package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.LearnDestination
import com.cornellappdev.hustle.ui.navigation.LearnTab

fun NavGraphBuilder.learnNavGraph(navController: NavHostController) {
    navigation<LearnTab>(startDestination = LearnDestination.Workshops) {
        composable<LearnDestination.Workshops> {}
    }
}