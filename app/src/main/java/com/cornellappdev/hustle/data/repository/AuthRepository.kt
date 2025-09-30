package com.cornellappdev.hustle.data.repository

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.cornellappdev.hustle.BuildConfig
import com.cornellappdev.hustle.data.model.user.InvalidEmailDomainException
import com.cornellappdev.hustle.data.model.user.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface AuthRepository {
    val currentUserFlow: StateFlow<User?>
    suspend fun signInWithGoogle(): Result<User>
    suspend fun signOut(): Result<Unit>
}

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    @ApplicationContext private val context: Context
) : AuthRepository {
    private val _currentUserFlow = MutableStateFlow<User?>(null)
    override val currentUserFlow: StateFlow<User?> = _currentUserFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {
            _currentUserFlow.value = it.currentUser?.toUser()
        }
    }

    override suspend fun signInWithGoogle(): Result<User> = runCatching {
        val credential = getGoogleCredential().getOrThrow()
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

        val email = googleIdTokenCredential.id
        if (!isValidCornellEmail(email)) {
            throw InvalidEmailDomainException("Please sign in with your Cornell email")
        }

        val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
        val authResult = firebaseAuth.signInWithCredential(authCredential).await()

        authResult.user?.toUser() ?: throw Exception("Authentication failed")
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        firebaseAuth.signOut()
        val clearRequest = ClearCredentialStateRequest()
        credentialManager.clearCredentialState(clearRequest)
    }

    private suspend fun getGoogleCredential(): Result<CustomCredential> = runCatching {
        val googleIdOption =
            GetGoogleIdOption.Builder().setServerClientId(BuildConfig.GOOGLE_AUTH_WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false).build()

        val request = GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()

        val result = credentialManager.getCredential(context, request)
        val credential = result.credential

        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            credential
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
