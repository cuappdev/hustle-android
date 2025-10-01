package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.Onboarding
import com.cornellappdev.hustle.ui.navigation.ProfileDestination
import com.cornellappdev.hustle.ui.navigation.ProfileTab
import com.cornellappdev.hustle.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation<ProfileTab>(startDestination = ProfileDestination.Profile) {
        composable<ProfileDestination.Profile> {
            ProfileScreen(navigateToSignIn = {
                navController.navigate(Onboarding) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }

        composable<ProfileDestination.EditProfile> {

        }
    }
}