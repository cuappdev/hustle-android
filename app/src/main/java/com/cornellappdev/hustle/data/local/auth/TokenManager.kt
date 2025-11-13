package com.cornellappdev.hustle.data.local.auth

import androidx.datastore.core.DataStore
import com.cornellappdev.hustle.data.model.user.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages the secure storage and retrieval of authentication tokens (access and refresh tokens)
 * using an encrypted DataStore.
 */
@Singleton
class TokenManager @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) {
    suspend fun getAccessToken(): String? {
        return userPreferencesDataStore.data.map {
            it.accessToken
        }.first()
    }

    suspend fun getRefreshToken(): String? {
        return userPreferencesDataStore.data.map {
            it.refreshToken
        }.first()
    }

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String
    ) {
        userPreferencesDataStore.updateData { preferences ->
            preferences.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    suspend fun clearTokens() {
        userPreferencesDataStore.updateData { preferences ->
            preferences.copy(
                accessToken = null,
                refreshToken = null
            )
        }
    }
}