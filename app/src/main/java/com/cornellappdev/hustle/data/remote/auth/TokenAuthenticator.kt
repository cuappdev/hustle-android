package com.cornellappdev.hustle.data.remote.auth

import android.util.Log
import com.cornellappdev.hustle.data.local.auth.TokenManager
import com.cornellappdev.hustle.data.model.user.RefreshTokenRequest
import com.cornellappdev.hustle.data.model.user.VerifyTokenRequest
import com.cornellappdev.hustle.data.repository.auth.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

/**
 * An [Authenticator] that handles token expiration and refresh logic for HTTP requests.
 */
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApiService: AuthApiService,
    private val firebaseAuth: FirebaseAuth,
    private val sessionManager: SessionManager
) : Authenticator {
    // Mutex to ensure only one token refresh at a time
    private val mutex = Mutex()

    override fun authenticate(route: Route?, response: Response): Request? {
        // Prevent infinite loops by limiting the number of retries
        if (getResponseCount(response) >= 3) {
            return null
        }

        return runBlocking {
            mutex.withLock {
                val currentToken = tokenManager.getAccessToken()
                val failedToken = response.request.header("Authorization")?.removePrefix("Bearer ")

                // If the token has been updated since the request was made, use the new token and skip refresh flow
                if (currentToken != null && currentToken != failedToken) {
                    return@runBlocking buildAuthRequest(response, currentToken)
                }

                // 1. Try to refresh the access token using the refresh token
                // 2. If that fails, try to re-authenticate with the Firebase token
                // 3. If that also fails, sign the user out and notify session expiration
                tryRefreshToken(response)
                    ?: tryFirebaseReAuthentication(response)
                    ?: handleAuthFailure()
            }
        }
    }

    private suspend fun tryRefreshToken(response: Response): Request? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null

        return runCatching {
            authApiService.refreshToken(RefreshTokenRequest(refreshToken))
                .takeIf { it.isSuccessful }
                ?.body()
                ?.let { tokenData ->
                    tokenManager.saveTokens(
                        accessToken = tokenData.accessToken,
                        refreshToken = tokenData.refreshToken
                    )
                    buildAuthRequest(response, tokenData.accessToken)
                }
        }.onFailure {
            Log.e(TAG, "Token refresh failed: ${it.message}")
        }.getOrNull()
    }

    private suspend fun tryFirebaseReAuthentication(response: Response): Request? {
        val firebaseUser = firebaseAuth.currentUser ?: return null

        return runCatching {
            val firebaseToken = firebaseUser.getIdToken(true).await().token ?: return null

            authApiService.verifyToken(VerifyTokenRequest(firebaseToken))
                .takeIf { it.isSuccessful }
                ?.body()
                ?.let { tokenData ->
                    tokenManager.saveTokens(
                        accessToken = tokenData.accessToken,
                        refreshToken = tokenData.refreshToken
                    )
                    buildAuthRequest(response, tokenData.accessToken)
                }
        }.onFailure {
            Log.e(TAG, "Firebase re-authentication failed: ${it.message}")
        }.getOrNull()
    }

    private suspend fun handleAuthFailure(): Request? {
        tokenManager.clearTokens()
        firebaseAuth.signOut()
        sessionManager.notifySessionExpired()
        return null
    }

    private fun buildAuthRequest(response: Response, token: String): Request =
        response.request.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

    private fun getResponseCount(response: Response): Int =
        generateSequence(response) { it.priorResponse }.count()

    companion object {
        private const val TAG = "TokenAuthenticator"
    }
}