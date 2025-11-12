package com.cornellappdev.hustle.data.remote.auth

import com.cornellappdev.hustle.data.local.auth.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = runBlocking {
            tokenManager.getAccessToken()
        } ?: return chain.proceed(originalRequest)

        return chain.proceed(
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}