package com.cornellappdev.hustle.ui.components.general

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cornellappdev.hustle.R

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            painter = painterResource(R.drawable.ic_chevron),
            contentDescription = "Back Button",
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackButtonPreview() {
    BackButton(onClick = {})
}