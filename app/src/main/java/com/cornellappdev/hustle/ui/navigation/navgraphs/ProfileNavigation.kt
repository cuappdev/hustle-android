package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.ProfileDestination
import com.cornellappdev.hustle.ui.navigation.ProfileTab

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation<ProfileTab>(startDestination = ProfileDestination.Profile) {
        composable<ProfileDestination.Profile> {

        }

        composable<ProfileDestination.EditProfile> {

        }
    }
}