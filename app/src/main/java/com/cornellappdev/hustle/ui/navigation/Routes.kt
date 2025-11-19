package com.cornellappdev.hustle.ui.navigation

import com.cornellappdev.hustle.util.constants.CategoryType
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