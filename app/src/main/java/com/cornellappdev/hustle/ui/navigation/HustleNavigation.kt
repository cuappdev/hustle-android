package com.cornellappdev.hustle.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cornellappdev.hustle.ui.navigation.navgraphs.homeNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.messagesNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.onboardingNavGraph
import com.cornellappdev.hustle.ui.navigation.navgraphs.profileNavGraph
import com.cornellappdev.hustle.ui.viewmodels.onboarding.AuthViewModel

@Composable
fun HustleNavigation(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val authUiState = authViewModel.collectUiStateValue()
    val startDestination = if (authUiState.isSignedIn) HomeTab else Onboarding

    LaunchedEffect(authUiState.isSignedIn) {
        if (authUiState.isSignedIn) {
            navController.navigate(HomeTab) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(Onboarding) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (authUiState.isSignedIn) {
                BottomNavigationBar(navController = navController)
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            onboardingNavGraph(
                signInWithGoogle = authViewModel::signInWithGoogle,
                isLoading = authUiState.isLoading,
                errorMessage = authUiState.errorMessage,
                clearError = authViewModel::clearError

            )
            homeNavGraph(navController = navController)
            messagesNavGraph(navController = navController)
            profileNavGraph(
                onSignOut = {
                    authViewModel.signOut()
                })
        }
    }

}