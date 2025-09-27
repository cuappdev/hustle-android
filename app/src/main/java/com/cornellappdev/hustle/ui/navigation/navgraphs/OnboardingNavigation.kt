package com.cornellappdev.hustle.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cornellappdev.hustle.ui.navigation.Onboarding
import com.cornellappdev.hustle.ui.navigation.OnboardingDestination
import com.cornellappdev.hustle.ui.screens.onboarding.SignInScreen

fun NavGraphBuilder.onboardingNavGraph(
    signInWithGoogle: () -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    clearError: () -> Unit

) {
    navigation<Onboarding>(startDestination = OnboardingDestination.SignIn) {
        composable<OnboardingDestination.SignIn> {
            SignInScreen(
                signInWithGoogle = signInWithGoogle,
                isLoading = isLoading,
                errorMessage = errorMessage,
                clearError = clearError
            )
        }
    }
}