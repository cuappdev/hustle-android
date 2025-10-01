package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.HomeTab
import com.cornellappdev.hustle.ui.navigation.Onboarding
import com.cornellappdev.hustle.ui.navigation.OnboardingDestination
import com.cornellappdev.hustle.ui.screens.onboarding.SignInScreen

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation<Onboarding>(startDestination = OnboardingDestination.SignIn) {
        composable<OnboardingDestination.SignIn> {
            SignInScreen(navigateToHome = {
                navController.navigate(HomeTab) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }
}