package com.cornellappdev.hustle.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.cornellappdev.hustle.ui.viewmodels.onboarding.AuthUiState
import com.cornellappdev.hustle.ui.viewmodels.onboarding.AuthViewModel

@Composable
fun ProfileScreen(
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authUiState = authViewModel.collectUiStateValue()

    ProfileScreenContent(
        signOut = onSignOut, authUiState = authUiState, modifier = modifier
    )
}

@Composable
private fun ProfileScreenContent(
    signOut: () -> Unit, authUiState: AuthUiState, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // User Info
        authUiState.user?.let { user ->
            AsyncImage(
                model = user.photoUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Text(
                text = user.displayName ?: "Unknown User"
            )

            Text(
                text = user.email ?: ""
            )
        }

        // Sign Out Button
        OutlinedButton(
            onClick = { signOut() },
            enabled = !authUiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (authUiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp), strokeWidth = 2.dp
                )
            } else {
                Text("Sign Out")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreenContent(
        signOut = {}, authUiState = AuthUiState(
            isLoading = false, user = null
        )
    )
}