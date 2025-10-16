package com.cornellappdev.hustle.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.ui.components.general.ErrorMessage
import com.cornellappdev.hustle.ui.components.onboarding.SignInButton
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.InstrumentSans
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(HustleColors.hustleGreen)
            .padding(
                horizontal = HustleSpacing.leftRightMargin
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                89.dp, alignment = Alignment.CenterVertically
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(HustleSpacing.medium)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hustle_logo),
                    contentDescription = "Hustle Logo",
                    tint = Color.Unspecified
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(HustleSpacing.extraSmall)
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = HustleColors.white,
                                    fontFamily = InstrumentSans,
                                    fontSize = 36.sp,
                                )
                            ) {
                                append("Welcome to ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = HustleColors.white,
                                    fontFamily = InstrumentSans,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic
                                )
                            ) {
                                append("Hustle")
                            }
                        }
                    )
                    Text(
                        text = "Browse. Buy. Book.",
                        color = HustleColors.white,
                        fontFamily = InstrumentSans,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            SignInButton(
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