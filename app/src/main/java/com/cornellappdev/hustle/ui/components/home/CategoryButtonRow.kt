package com.cornellappdev.hustle.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cornellappdev.hustle.ui.components.general.HustleButton
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.util.constants.CategoryType
import com.cornellappdev.hustle.util.constants.SERVICE_CATEGORIES

@Composable
fun CategoryButtonRow(
    onCategoryClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = HustleSpacing.large)
) {
    val lazyListState = rememberLazyListState()
    LazyRow(
        state = lazyListState,
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(HustleSpacing.extraSmall)
    ) {
        items(SERVICE_CATEGORIES, key = { it.categoryType }) { category ->
            val categoryType = category.categoryType
            HustleButton(
                onClick = { onCategoryClick(categoryType) },
                text = categoryType.typeName,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = category.iconResId),
                        contentDescription = categoryType.typeName,
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryButtonRowPreview() {
    CategoryButtonRow(
        onCategoryClick = {}
    )
}