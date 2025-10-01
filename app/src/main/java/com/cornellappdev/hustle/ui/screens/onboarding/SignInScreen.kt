package com.cornellappdev.hustle.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cornellappdev.hustle.ui.components.general.ErrorMessage
import com.cornellappdev.hustle.ui.components.onboarding.GoogleSignInButton
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.onboarding.SignInScreenViewModel

@Composable
fun SignInScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    signInScreenViewModel: SignInScreenViewModel = hiltViewModel()
) {
    val signInUiState = signInScreenViewModel.collectUiStateValue()

    LaunchedEffect(signInUiState.isSignedIn) {
        if (signInUiState.isSignedIn) navigateToHome()
    }

    SignInScreenContent(
        onGoogleSignInButtonClick = signInScreenViewModel::signInWithGoogle,
        isSignInLoading = signInUiState.actionState is ActionState.Loading,
        errorMessage = when (signInUiState.actionState) {
            is ActionState.Error -> signInUiState.actionState.message
            else -> null
        },
        onDismissError = signInScreenViewModel::clearActionState,
        modifier = modifier
    )
}

@Composable
private fun SignInScreenContent(
    onGoogleSignInButtonClick: () -> Unit,
    isSignInLoading: Boolean,
    errorMessage: String?,
    onDismissError: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            GoogleSignInButton(
                onClick = onGoogleSignInButtonClick, isLoading = isSignInLoading
            )
        }
        errorMessage?.let { error ->
            ErrorMessage(
                message = error,
                onDismiss = onDismissError,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

class SignInErrorMessageProvider : PreviewParameterProvider<String?> {
    override val values = sequenceOf(
        null,
        "Sign in failed. Please sign in with your Cornell email"
    )
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview(
    @PreviewParameter(SignInErrorMessageProvider::class) errorMessage: String?
) {
    SignInScreenContent(
        onGoogleSignInButtonClick = {},
        isSignInLoading = false,
        errorMessage = errorMessage,
        onDismissError = {})
}