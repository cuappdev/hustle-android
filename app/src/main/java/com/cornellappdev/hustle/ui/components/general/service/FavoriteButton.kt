package com.cornellappdev.hustle.ui.components.general.service

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.R
import kotlinx.coroutines.launch

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fillAlpha by animateFloatAsState(
        targetValue = if (isFavorite) 1f else 0f,
        animationSpec = tween(200),
        label = "Fill Alpha"
    )

    val scale = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()

    IconButton(
        onClick = {
            if (!isFavorite) {
                coroutineScope.launch {
                    // Animate the button to scale up and then back to normal size
                    scale.animateTo(1.2f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
                    scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
                }
            }
            onClick()
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_favorite_outline),
                contentDescription = "Favorite Outline",
                tint = Color.Unspecified
            )

            Icon(
                painter = painterResource(R.drawable.ic_favorite_filled),
                contentDescription = "Favorite Filled",
                tint = Color.Unspecified,
                modifier = Modifier.graphicsLayer { alpha = fillAlpha }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteButtonPreview() {
    var isFavorite by remember { mutableStateOf(false) }

    FavoriteButton(
        isFavorite = isFavorite,
        onClick = { isFavorite = !isFavorite },
        modifier = Modifier.size(32.dp)
    )
}