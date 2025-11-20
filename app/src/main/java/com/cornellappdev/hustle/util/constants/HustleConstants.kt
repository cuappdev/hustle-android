package com.cornellappdev.hustle.util.constants

import androidx.annotation.DrawableRes
import com.cornellappdev.hustle.R
import kotlinx.serialization.Serializable

//TODO: Separate into different constant files or data package if necessary
@Serializable
enum class CategoryType(val typeName: String) {
    LESSONS("Lessons"),
    PHOTO("Photo"),
    BEAUTY("Beauty"),
    PROFESSIONAL("Professional"),
    POPULAR_RIGHT_NOW("Popular right now"),
    NEW_ON_HUSTLE("New on Hustle"),
    SERVICES_NEAR_YOU("Services near you"),
    AVAILABLE_THIS_WEEK("Available this week");
}
data class ServiceCategory(
    val categoryType: CategoryType,
    @DrawableRes val iconResId: Int
)

val SERVICE_CATEGORIES = listOf(
    ServiceCategory(CategoryType.LESSONS, R.drawable.ic_lessons),
    ServiceCategory(CategoryType.PHOTO, R.drawable.ic_photo),
    ServiceCategory(CategoryType.BEAUTY, R.drawable.ic_beauty),
    ServiceCategory(CategoryType.PROFESSIONAL, R.drawable.ic_professional)
)