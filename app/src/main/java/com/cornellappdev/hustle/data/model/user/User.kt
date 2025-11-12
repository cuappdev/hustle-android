package com.cornellappdev.hustle.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO: Add other fields from backend UserResponse as necessary and remove unused fields
data class User(
    val firebaseUid: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?
)

@Serializable
data class UserResponse(
    val id: String,
    @SerialName("firebase_uid") val firebaseUid: String,
    val email: String,
    @SerialName("firstname") val firstName: String,
    @SerialName("lastname") val lastName: String
)

class InvalidEmailDomainException(message: String) : Exception(message)