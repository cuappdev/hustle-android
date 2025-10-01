package com.cornellappdev.hustle.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.hustle.ui.navigation.navgraphs.homeNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.messagesNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.onboardingNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.profileNavGraph

@Composable
fun HustleNavigation(
    isSignedIn: Boolean = false
) {
    val navController = rememberNavController()
    val startDestination = if (isSignedIn) HomeTab else Onboarding

    Scaffold(
        bottomBar = {
            if (isSignedIn) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            onboardingNavGraph(navController = navController)
            homeNavGraph(navController = navController)
            messagesNavGraph(navController = navController)
            profileNavGraph(navController = navController)
        }
    }

}