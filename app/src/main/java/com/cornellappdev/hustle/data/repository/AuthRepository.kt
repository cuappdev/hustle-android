package com.cornellappdev.hustle.data.repository

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.cornellappdev.hustle.BuildConfig
import com.cornellappdev.hustle.data.model.user.AuthResult
import com.cornellappdev.hustle.data.model.user.InvalidEmailDomainException
import com.cornellappdev.hustle.data.model.user.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface AuthRepository {
    suspend fun signInWithGoogle(): AuthResult<User>
    suspend fun signOut(): AuthResult<Unit>
    fun getCurrentUser(): User?
    fun isUserSignedIn(): Boolean
}

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    @ApplicationContext private val context: Context
) : AuthRepository {
    override suspend fun signInWithGoogle(): AuthResult<User> {
        return try {
            val credential = getGoogleCredential()
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            val email = googleIdTokenCredential.id
            if (!isValidCornellEmail(email)) {
                return AuthResult.Error(
                    InvalidEmailDomainException("Please sign in with your Cornell email")
                )
            }

            val authCredential =
                GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

            val authResult = firebaseAuth.signInWithCredential(authCredential).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                AuthResult.Success(firebaseUser.toUser())
            } else {
                AuthResult.Error(Exception("Authentication failed"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Google sign in failed", e)
            AuthResult.Error(e)
        }
    }

    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            firebaseAuth.signOut()

            val clearRequest = ClearCredentialStateRequest()
            credentialManager.clearCredentialState(clearRequest)

            AuthResult.Success(Unit)
        } catch (e: Exception) {
            Log.e("AuthRepository", "Sign out failed", e)
            AuthResult.Error(e)
        }
    }

    override fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.toUser()
    }

    override fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    private suspend fun getGoogleCredential(): CustomCredential {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.GOOGLE_AUTH_WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false).build()

        val request = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()

        val result = credentialManager.getCredential(
            context = context, request = request
        )

        val credential = result.credential
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            return credential
        } else {
            throw Exception("Invalid credential type")
        }
    }

    private fun FirebaseUser.toUser(): User {
        return User(
            firebaseUid = uid,
            email = email,
            displayName = displayName,
            photoUrl = photoUrl?.toString()
        )
    }

    private fun isValidCornellEmail(email: String): Boolean {
        return email.endsWith("@cornell.edu", ignoreCase = true)
    }
}
