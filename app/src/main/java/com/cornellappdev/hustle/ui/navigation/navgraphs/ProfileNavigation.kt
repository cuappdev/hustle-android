package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.data.model.user.User
import com.cornellappdev.hustle.ui.navigation.ProfileDestination
import com.cornellappdev.hustle.ui.navigation.ProfileTab
import com.cornellappdev.hustle.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    user: User?,
    onSignOut: () -> Unit,
    isLoading: Boolean
) {
    navigation<ProfileTab>(startDestination = ProfileDestination.Profile) {
        composable<ProfileDestination.Profile> {
            ProfileScreen(
                user = user,
                onSignOut = onSignOut,
                isLoading = isLoading
            )
        }

        composable<ProfileDestination.EditProfile> {

        }
    }
}