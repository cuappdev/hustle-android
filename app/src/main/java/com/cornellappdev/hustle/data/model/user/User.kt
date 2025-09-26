package com.cornellappdev.hustle.data.model.user

data class User(
    val firebaseUid: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?,
)
