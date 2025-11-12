package com.cornellappdev.hustle.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val accessToken: String? = null,
    val refreshToken: String? = null
)