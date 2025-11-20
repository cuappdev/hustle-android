package com.cornellappdev.hustle.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.data.model.services.Service
import com.cornellappdev.hustle.ui.components.general.ClickableSectionHeader
import com.cornellappdev.hustle.ui.components.general.service.ServiceHorizontalCarouselSection
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.util.constants.CategoryType
import com.cornellappdev.hustle.util.constants.TEST_SERVICES

@Composable
fun MainContent(
    popularRightNowListings: List<Service>,
    newOnHustleListings: List<Service>,
    servicesNearYouListings: List<Service>,
    availableThisWeekListings: List<Service>,
    navigateToCategorySubpage: (CategoryType) -> Unit,
    navigateToServiceDetail: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = HustleSpacing.small),
    ) {
        item {
            CategoryButtonRow(onCategoryClick = navigateToCategorySubpage)
        }
        item {
            Spacer(modifier = Modifier.height(HustleSpacing.medium))
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(HustleSpacing.large)
            ) {
                ServiceHorizontalCarouselSection(
                    serviceListings = popularRightNowListings,
                    onServiceClick = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick,
                    header = {
                        ClickableSectionHeader(
                            title = CategoryType.POPULAR_RIGHT_NOW.typeName,
                            onClick = {
                                navigateToCategorySubpage(CategoryType.POPULAR_RIGHT_NOW)
                            },
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }
                )

                ServiceHorizontalCarouselSection(
                    serviceListings = newOnHustleListings,
                    onServiceClick = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick,
                    header = {
                        ClickableSectionHeader(
                            title = CategoryType.NEW_ON_HUSTLE.typeName,
                            onClick = {
                                navigateToCategorySubpage(CategoryType.NEW_ON_HUSTLE)
                            },
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }
                )

                ServiceHorizontalCarouselSection(
                    serviceListings = servicesNearYouListings,
                    onServiceClick = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick,
                    header = {
                        ClickableSectionHeader(
                            title = CategoryType.SERVICES_NEAR_YOU.typeName,
                            onClick = {
                                navigateToCategorySubpage(CategoryType.SERVICES_NEAR_YOU)
                            },
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }
                )

                ServiceHorizontalCarouselSection(
                    serviceListings = availableThisWeekListings,
                    onServiceClick = navigateToServiceDetail,
                    onFavoriteClick = onFavoriteClick,
                    header = {
                        ClickableSectionHeader(
                            title = CategoryType.AVAILABLE_THIS_WEEK.typeName,
                            onClick = {
                                navigateToCategorySubpage(CategoryType.AVAILABLE_THIS_WEEK)
                            },
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    HustleTheme {
        MainContent(
            popularRightNowListings = TEST_SERVICES,
            newOnHustleListings = TEST_SERVICES,
            servicesNearYouListings = TEST_SERVICES,
            availableThisWeekListings = TEST_SERVICES,
            navigateToCategorySubpage = {},
            navigateToServiceDetail = {},
            onFavoriteClick = {},
        )
    }
}