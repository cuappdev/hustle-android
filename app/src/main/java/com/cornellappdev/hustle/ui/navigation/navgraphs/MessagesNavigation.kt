package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.FavoritesDestination
import com.cornellappdev.hustle.ui.navigation.FavoritesTab

fun NavGraphBuilder.favoritesNavGraph(navController: NavHostController) {
    navigation<FavoritesTab>(startDestination = FavoritesDestination.Favorites) {
        composable<FavoritesDestination.Favorites> {}
    }
}