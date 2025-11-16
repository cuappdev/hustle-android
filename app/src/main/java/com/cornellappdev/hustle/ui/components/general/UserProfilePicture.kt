package com.cornellappdev.hustle.ui.components.general

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.cornellappdev.hustle.ui.theme.HustleColors

@Composable
fun UserProfilePicture(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    // TODO: Add loading and error states
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "User Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
private fun UserProfilePicturePreview() {
    UserProfilePicture(
        imageUrl = "",
        modifier = Modifier
            .size(22.dp)
            .border(1.dp, HustleColors.secondaryGray, CircleShape)
    )
}