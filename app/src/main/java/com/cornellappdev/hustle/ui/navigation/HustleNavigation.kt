package com.cornellappdev.hustle.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.hustle.ui.navigation.navgraphs.favoritesNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.homeNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.profileNavGraph

@Composable
fun HustleNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeTab,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeNavGraph(navController = navController)
            favoritesNavGraph(navController = navController)
            profileNavGraph(navController = navController)
        }
    }

}