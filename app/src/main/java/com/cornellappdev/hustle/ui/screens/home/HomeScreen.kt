package com.cornellappdev.hustle.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cornellappdev.hustle.data.model.services.Service
import com.cornellappdev.hustle.ui.components.home.MainContent
import com.cornellappdev.hustle.ui.components.home.SearchContent
import com.cornellappdev.hustle.ui.components.home.SearchHeader
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.ui.viewmodels.home.HomeScreenViewModel
import com.cornellappdev.hustle.util.constants.CategoryType
import com.cornellappdev.hustle.util.constants.TEST_RECENT_SEARCHES
import com.cornellappdev.hustle.util.constants.TEST_SERVICES

@Composable
fun HomeScreen(
    navigateToCategorySubpage: (CategoryType) -> Unit,
    navigateToServiceDetail: (Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    // TODO: Add states to viewmodel and replace test data with real data from viewmodel
    val queryState = rememberTextFieldState()
    var isSearchActive by remember { mutableStateOf(false) }
    val popularRightNowListings = TEST_SERVICES
    val newOnHustleListings = TEST_SERVICES
    val servicesNearYouListings = TEST_SERVICES
    val availableThisWeekListings = TEST_SERVICES
    val recentSearches = TEST_RECENT_SEARCHES
    val recentlyViewedServiceListings = TEST_SERVICES

    HomeScreenContent(
        homeScreenViewState = HomeScreenViewState(
            queryState = queryState,
            isSearchActive = isSearchActive,
            popularRightNowListings = popularRightNowListings,
            newOnHustleListings = newOnHustleListings,
            servicesNearYouListings = servicesNearYouListings,
            availableThisWeekListings = availableThisWeekListings,
            recentSearches = recentSearches,
            recentlyViewedServices = recentlyViewedServiceListings
        ),
        onSearchActiveChange = { isActive -> isSearchActive = isActive },
        onSearch = {},
        onSearchSuggestionClick = {},
        navigateToCategorySubpage = navigateToCategorySubpage,
        navigateToServiceDetail = navigateToServiceDetail,
        onFavoriteClick = {}
    )
}

data class HomeScreenViewState(
    val queryState: TextFieldState,
    val isSearchActive: Boolean,
    val popularRightNowListings: List<Service>,
    val newOnHustleListings: List<Service>,
    val servicesNearYouListings: List<Service>,
    val availableThisWeekListings: List<Service>,
    val recentSearches: List<String>,
    val recentlyViewedServices: List<Service>,
)

@Composable
private fun HomeScreenContent(
    homeScreenViewState: HomeScreenViewState,
    onSearchActiveChange: (Boolean) -> Unit,
    onSearch: () -> Unit,
    onSearchSuggestionClick: (String) -> Unit,
    navigateToCategorySubpage: (CategoryType) -> Unit,
    navigateToServiceDetail: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchHeader(
            queryState = homeScreenViewState.queryState,
            isSearchActive = homeScreenViewState.isSearchActive,
            onSearchActiveChange = onSearchActiveChange,
            onSearch = onSearch,
            modifier = Modifier.padding(horizontal = HustleSpacing.large)
        )
        AnimatedContent(
            targetState = homeScreenViewState.isSearchActive,
            label = "HomeScreenContentAnimation",
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith fadeOut(
                    animationSpec = tween(
                        300
                    )
                )
            },
        ) { isSearchActive ->
            if (!isSearchActive) {
                MainContent(
                    popularRightNowListings = homeScreenViewState.popularRightNowListings,
                    newOnHustleListings = homeScreenViewState.newOnHustleListings,
                    servicesNearYouListings = homeScreenViewState.servicesNearYouListings,
                    availableThisWeekListings = homeScreenViewState.availableThisWeekListings,
                    navigateToCategorySubpage = navigateToCategorySubpage,
                    navigateToServiceDetail = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick
                )
            } else {
                SearchContent(
                    recentSearches = homeScreenViewState.recentSearches,
                    recentlyViewedServices = homeScreenViewState.recentlyViewedServices,
                    onSearchSuggestionClick = onSearchSuggestionClick,
                    navigateToServiceDetail = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val queryState = rememberTextFieldState()
    var isSearchActive by remember { mutableStateOf(false) }
    HustleTheme {
        HomeScreenContent(
            homeScreenViewState = HomeScreenViewState(
                queryState = queryState,
                isSearchActive = isSearchActive,
                popularRightNowListings = TEST_SERVICES,
                newOnHustleListings = TEST_SERVICES,
                servicesNearYouListings = TEST_SERVICES,
                availableThisWeekListings = TEST_SERVICES,
                recentSearches = TEST_RECENT_SEARCHES,
                recentlyViewedServices = TEST_SERVICES
            ),
            onSearchActiveChange = { isActive -> isSearchActive = isActive },
            onSearch = {},
            onSearchSuggestionClick = {},
            navigateToCategorySubpage = {},
            navigateToServiceDetail = {},
            onFavoriteClick = {}
        )
    }
}