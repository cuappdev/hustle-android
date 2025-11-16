package com.cornellappdev.hustle.data.model.services

import com.cornellappdev.hustle.data.model.user.User

//TODO: Update model fields once API is finalized
data class Service(
    val id: Int,
    val name: String,
    val category: String,
    val minimumPrice: Double,
    val priceUnit: String = "",
    val rating: Double,
    val displayImageUrl: String,
    val isFavorited: Boolean,
    val user: User
)

