package com.cornellappdev.hustle.ui.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.data.model.services.Service
import com.cornellappdev.hustle.ui.components.general.service.ServiceHorizontalCarouselSection
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.util.constants.TEST_RECENT_SEARCHES
import com.cornellappdev.hustle.util.constants.TEST_SERVICES

@Composable
fun SearchContent(
    recentSearches: List<String>,
    recentlyViewedServices: List<Service>,
    onSearchSuggestionClick: (String) -> Unit,
    navigateToServiceDetail: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        AnimatedVisibility(
            visible = recentSearches.isNotEmpty(),
            label = "Recent Searches Visibility",
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            RecentSearchesSection(recentSearches, onSearchSuggestionClick)
        }

        Spacer(modifier = Modifier.height(44.dp))

        AnimatedVisibility(
            visible = recentlyViewedServices.isNotEmpty(),
            label = "Recently Viewed Services Visibility",
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            ServiceHorizontalCarouselSection(
                serviceListings = recentlyViewedServices,
                onServiceClick = navigateToServiceDetail,
                onFavoriteClick = onFavoriteClick,
                header = {
                    Text(
                        text = "Recently viewed",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(start = HustleSpacing.large)
                    )
                }
            )
        }
    }
}

@Composable
private fun RecentSearchesSection(
    recentSearches: List<String>,
    onSearchSuggestionClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = HustleSpacing.large)
    ) {
        Text(
            text = "Recent",
            style = MaterialTheme.typography.headlineSmall
        )

        recentSearches.forEach { recentSearch ->
            RecentSearchItem(
                recentSearch = recentSearch,
                onSearchSuggestionClick = onSearchSuggestionClick
            )
        }
    }
}

@Composable
private fun RecentSearchItem(
    recentSearch: String,
    onSearchSuggestionClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(HustleSpacing.small),
        modifier = Modifier
            .clickable(
                onClick = {
                    onSearchSuggestionClick(recentSearch)
                }
            )
            .padding(top = HustleSpacing.medium)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_history),
                contentDescription = "Recent Search Icon",
                tint = Color.Unspecified
            )
            Text(
                text = recentSearch,
                style = MaterialTheme.typography.labelLarge
            )
        }
        HorizontalDivider(color = HustleColors.iconInactive)
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchContentPreview() {
    val recentSearches = TEST_RECENT_SEARCHES
    val recentlyViewedServiceListings = TEST_SERVICES
    HustleTheme {
        SearchContent(
            recentSearches = recentSearches,
            recentlyViewedServices = recentlyViewedServiceListings,
            onSearchSuggestionClick = {},
            navigateToServiceDetail = {},
            onFavoriteClick = {}
        )
    }
}