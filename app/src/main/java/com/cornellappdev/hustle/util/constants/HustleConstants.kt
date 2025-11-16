package com.cornellappdev.hustle.util.constants

import androidx.annotation.DrawableRes
import com.cornellappdev.hustle.R

data class ServiceCategory(
    val name: String, @DrawableRes
    val iconResId: Int
)

val SERVICE_CATEGORIES = listOf(
    ServiceCategory("Lessons", R.drawable.ic_lessons),
    ServiceCategory("Photo", R.drawable.ic_photo),
    ServiceCategory("Beauty", R.drawable.ic_beauty),
    ServiceCategory("Professional", R.drawable.ic_professional)
)