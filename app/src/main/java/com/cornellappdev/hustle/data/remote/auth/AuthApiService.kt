package com.cornellappdev.hustle.data.remote.auth

import com.cornellappdev.hustle.data.model.user.RefreshTokenRequest
import com.cornellappdev.hustle.data.model.user.RefreshTokenResponse
import com.cornellappdev.hustle.data.model.user.VerifyTokenRequest
import com.cornellappdev.hustle.data.model.user.VerifyTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/verify-token")
    suspend fun verifyToken(
        @Body request: VerifyTokenRequest
    ): Response<VerifyTokenResponse>

    @POST("api/refresh-token")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): Response<RefreshTokenResponse>
}