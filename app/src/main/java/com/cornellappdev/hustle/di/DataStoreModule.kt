package com.cornellappdev.hustle.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.cornellappdev.hustle.data.local.auth.UserPreferencesSerializer
import com.cornellappdev.hustle.data.model.user.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userPreferencesDataStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_preferences",
    serializer = UserPreferencesSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.userPreferencesDataStore
    }
}