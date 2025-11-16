package com.cornellappdev.hustle.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VerifyTokenRequest(
    val token: String
)

@Serializable
data class VerifyTokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    val user: UserResponse
)

@Serializable
data class RefreshTokenRequest(
    @SerialName("refresh_token") val refreshToken: String
)

@Serializable
data class RefreshTokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String
)