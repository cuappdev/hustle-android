package com.cornellappdev.hustle.ui.components.general.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.data.model.services.Service
import com.cornellappdev.hustle.ui.components.general.ClickableSectionHeader
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.util.constants.TEST_SERVICES
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceHorizontalCarouselSection(
    serviceListings: List<Service>,
    onServiceClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {},
) {
    val carouselState = rememberCarouselState() { serviceListings.size }
    Column(
        verticalArrangement = Arrangement.spacedBy(HustleSpacing.medium),
        modifier = modifier
    ) {
        header()
        HorizontalUncontainedCarousel(
            state = carouselState,
            itemWidth = 186.dp,
            itemSpacing = 4.dp,
            contentPadding = PaddingValues(horizontal = HustleSpacing.large),
        ) { i ->
            val service = serviceListings[i]
            ServiceCard(
                serviceImageUrl = service.displayImageUrl,
                userName = service.user.displayName ?: "",
                userProfileImageUrl = service.user.photoUrl ?: "",
                serviceTitle = service.name,
                serviceMinimumPriceCeiling = ceil(service.minimumPrice).toInt(),
                priceUnit = service.priceUnit,
                serviceRating = service.rating,
                onClick = { onServiceClick(service.id) },
                isFavorite = service.isFavorited,
                onFavoriteClick = { onFavoriteClick(service.id) },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ServiceHorizontalCarouselPreview() {
    HustleTheme {
        ServiceHorizontalCarouselSection(
            serviceListings = TEST_SERVICES,
            onServiceClick = {},
            onFavoriteClick = {},
            header = {
                ClickableSectionHeader(
                    title = "Recently viewed",
                    onClick = {},
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
        )
    }
}