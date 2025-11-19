package com.cornellappdev.hustle.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
data object Onboarding : AppDestination

@Serializable
data object HomeTab : AppDestination

@Serializable
data object LearnTab : AppDestination

@Serializable
data object MessagesTab : AppDestination

@Serializable
data object ProfileTab : AppDestination

sealed interface HomeDestination : AppDestination {
    @Serializable
    data object Home : HomeDestination

    @Serializable
    data class ServiceDetail(val serviceId: Int) : HomeDestination

    @Serializable
    data class CategoryServices(val categoryType: CategoryType) : HomeDestination
}

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

    companion object {
        fun fromTypeName(typeName: String): CategoryType {
            return entries.firstOrNull { it.typeName == typeName }
                ?: throw IllegalArgumentException("No CategoryType with typeName $typeName found.")
        }
    }
}

sealed interface LearnDestination : AppDestination {
    @Serializable
    data object Workshops : LearnDestination
}

sealed interface MessagesDestination : AppDestination {
    @Serializable
    data object Messages : MessagesDestination
}

sealed interface ProfileDestination : AppDestination {
    @Serializable
    data object Profile : ProfileDestination

    @Serializable
    data object EditProfile : ProfileDestination
}

sealed interface OnboardingDestination : AppDestination {
    @Serializable
    data object SignIn : OnboardingDestination
}