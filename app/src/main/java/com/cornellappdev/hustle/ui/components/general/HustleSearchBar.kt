package com.cornellappdev.hustle.ui.components.general

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme

@Composable
fun HustleSearchBar(
    queryState: TextFieldState,
    isSearchActive: Boolean,
    onFocus: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    TextField(
        state = queryState,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = MaterialTheme.typography.labelLarge.copy(
            color = HustleColors.secondaryGray,
            lineHeight = 18.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            ),
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .onFocusChanged { if (it.isFocused) onFocus() },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = HustleColors.shadedGray.copy(alpha = 0.47f),
            unfocusedContainerColor = HustleColors.shadedGray.copy(alpha = 0.47f),
            focusedTextColor = HustleColors.secondaryGray,
            unfocusedTextColor = HustleColors.secondaryGray,
            cursorColor = HustleColors.secondaryGray
        ),
        contentPadding = PaddingValues(
            horizontal = HustleSpacing.medium,
            vertical = HustleSpacing.extraSmall
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        onKeyboardAction = {
            onSearch()
            focusManager.clearFocus()
        },
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(HustleSpacing.extraSmall)
            ) {
                AnimatedVisibility(
                    visible = !isSearchActive,
                    enter = scaleIn() + fadeIn(),
                    exit = fadeOut(animationSpec = tween(100)) + shrinkHorizontally(
                        shrinkTowards = Alignment.Start,
                        animationSpec = tween(100)
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_leading),
                        contentDescription = "Search Leading Icon",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "Search services",
                    style = MaterialTheme.typography.labelLarge,
                    color = HustleColors.wash
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun HustleSearchBarPreview() {
    val queryState = rememberTextFieldState()
    var isSearchActive by remember { mutableStateOf(false) }
    HustleTheme {
        HustleSearchBar(
            queryState = queryState,
            isSearchActive = isSearchActive,
            onFocus = { isSearchActive = true },
            onSearch = {}
        )
    }
}