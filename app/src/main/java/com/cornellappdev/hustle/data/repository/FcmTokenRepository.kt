package com.cornellappdev.hustle.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface FcmTokenRepository {
    suspend fun getFcmToken(): Result<String>
    suspend fun updateFcmToken(token: String): Result<Unit>
}

@Singleton
class FcmTokenRepositoryImpl @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging,
    // TODO: Add your API service to send token to backend
) : FcmTokenRepository {

    override suspend fun getFcmToken(): Result<String> = runCatching {
        firebaseMessaging.token.await()
    }

    override suspend fun updateFcmToken(token: String): Result<Unit> = runCatching {
        // TODO: Send token to backend
    }
}