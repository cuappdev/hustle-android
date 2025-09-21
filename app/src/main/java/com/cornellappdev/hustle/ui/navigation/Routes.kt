package com.cornellappdev.hustle.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
data object HomeTab : AppDestination

@Serializable
data object FavoritesTab : AppDestination

@Serializable
data object ProfileTab : AppDestination

sealed interface HomeDestination : AppDestination {
    @Serializable
    data object Home : HomeDestination

    @Serializable
    data class ServiceDetail(val serviceId: String) : HomeDestination
}

sealed interface FavoritesDestination : AppDestination {
    @Serializable
    data object Favorites : FavoritesDestination
}

sealed interface ProfileDestination : AppDestination {
    @Serializable
    data object Profile : ProfileDestination

    @Serializable
    data object EditProfile : ProfileDestination
}