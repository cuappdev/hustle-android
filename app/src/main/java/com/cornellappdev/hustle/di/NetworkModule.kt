package com.cornellappdev.hustle.di

import com.cornellappdev.hustle.BuildConfig
import com.cornellappdev.hustle.data.remote.ExampleApiService
import com.cornellappdev.hustle.data.remote.auth.AuthApiService
import com.cornellappdev.hustle.data.remote.auth.AuthInterceptor
import com.cornellappdev.hustle.data.remote.auth.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_API_URL = BuildConfig.BASE_API_URL

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun Retrofit.Builder.applyBaseConfig(json: Json): Retrofit.Builder {
        val contentType = "application/json".toMediaType()
        return this
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BASE_API_URL)
    }

    @Provides
    @Singleton
    @Unauthenticated
    fun provideUnauthenticatedOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authenticator: TokenAuthenticator,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Singleton
    @Unauthenticated
    fun provideUnauthenticatedRetrofit(
        @Unauthenticated okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).applyBaseConfig(json).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).applyBaseConfig(json).build()
    }

    @Provides
    @Singleton
    fun provideExampleApiService(retrofit: Retrofit): ExampleApiService {
        return retrofit.create(ExampleApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(
        @Unauthenticated retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Unauthenticated