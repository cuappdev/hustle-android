package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.cornellappdev.hustle.ui.navigation.HomeDestination
import com.cornellappdev.hustle.ui.navigation.HomeTab
import com.cornellappdev.hustle.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation<HomeTab>(startDestination = HomeDestination.Home) {
        composable<HomeDestination.Home> {
            HomeScreen()
        }

        composable<HomeDestination.ServiceDetail> { backStackEntry ->
            val serviceId = backStackEntry.toRoute<HomeDestination.ServiceDetail>()
        }
    }
}