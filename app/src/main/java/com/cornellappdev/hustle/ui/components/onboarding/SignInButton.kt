package com.cornellappdev.hustle.ui.components.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.hustle.ui.theme.HelveticaNeue
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing

@Composable
fun SignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = HustleColors.accentGreen,
            contentColor = HustleColors.hustleGreen,
            disabledContainerColor = HustleColors.accentGreen,
            disabledContentColor = HustleColors.hustleGreen
        ),
        contentPadding = PaddingValues(
            vertical = HustleSpacing.medium
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(HustleSpacing.small)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = HustleColors.hustleGreen,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = if (isLoading) "Signing in..." else "Log in with NetID",
                fontFamily = HelveticaNeue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SignInButtonPreview() {
    SignInButton(onClick = {}, isLoading = true)
}