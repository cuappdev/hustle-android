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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.cornellappdev.hustle.data.model.user.User
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val profileUiState = profileScreenViewModel.collectUiStateValue()
    val user = profileUiState.user

    LaunchedEffect(user) {
        if (user == null) navigateToSignIn()
    }

    user?.let {
        ProfileScreenContent(
            user = it,
            onSignOut = profileScreenViewModel::signOut,
            isSignOutLoading = profileUiState.authActionState is ActionState.Loading,
            modifier = modifier
        )
    }
}

@Composable
private fun ProfileScreenContent(
    user: User,
    onSignOut: () -> Unit,
    isSignOutLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // User Info
        AsyncImage(
            model = user.photoUrl,
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(text = user.displayName ?: "Unknown User")

        Text(text = user.email ?: "")

        // Sign Out Button
        OutlinedButton(
            onClick = onSignOut,
            enabled = !isSignOutLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isSignOutLoading) {
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
        user = User(
            firebaseUid = "1",
            displayName = "John Doe",
            email = "jd123@cornell.edu",
            photoUrl = null
        ), onSignOut = {}, isSignOutLoading = false
    )
}