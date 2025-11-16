package com.cornellappdev.hustle.ui.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.ui.components.general.BackButton
import com.cornellappdev.hustle.ui.components.general.HustleSearchBar
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing

@Composable
fun SearchHeader(
    queryState: TextFieldState,
    isSearchActive: Boolean,
    onSearchActiveChange: (Boolean) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val animationProgress by animateFloatAsState(
        targetValue = if (isSearchActive) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 300),
        label = "SearchHeaderAnimation"
    )
    var headerHeightPx by remember { mutableIntStateOf(0) }
    var gapHeightPx by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        Text(
            text = "Hustle",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                color = HustleColors.hustleGreen
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { headerHeightPx = it.height }
                .graphicsLayer {
                    translationY = animationProgress * -headerHeightPx
                    alpha = 1.0f - animationProgress
                }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(HustleSpacing.extraSmall)
                .onSizeChanged { gapHeightPx = it.height }
        )
        SearchBarRow(
            isSearchActive,
            queryState,
            onBackClick = {
                onSearchActiveChange(false)
                focusManager.clearFocus()
            },
            onFocus = { onSearchActiveChange(true) },
            onSearch = onSearch,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    val totalDisplacement = headerHeightPx + gapHeightPx
                    translationY = animationProgress * -totalDisplacement
                }
        )
    }
}

@Composable
private fun SearchBarRow(
    isSearchActive: Boolean,
    queryState: TextFieldState,
    onBackClick: () -> Unit,
    onFocus: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(HustleSpacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = isSearchActive,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut(animationSpec = tween(150)) + shrinkHorizontally(
                shrinkTowards = Alignment.Start,
                animationSpec = tween(150)
            )
        ) {
            BackButton(onClick = onBackClick, modifier = Modifier.size(12.dp))
        }
        HustleSearchBar(
            queryState = queryState,
            isSearchActive = isSearchActive,
            onFocus = onFocus,
            onSearch = onSearch,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchHeaderPreview() {
    var isSearchActive by remember { mutableStateOf(false) }
    val queryState = rememberTextFieldState()
    SearchHeader(
        queryState = queryState,
        isSearchActive = isSearchActive,
        onSearchActiveChange = { isSearchActive = it },
        onSearch = {}
    )
}