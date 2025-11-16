package com.cornellappdev.hustle.ui.components.general.service

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.cornellappdev.hustle.ui.components.general.UserProfilePicture
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme
import com.cornellappdev.hustle.util.constants.TEST_SERVICES
import kotlin.math.ceil

enum class ServiceCardType {
    DEFAULT,
    RESULT
}

@Composable
fun ServiceCard(
    serviceImageUrl: String,
    userName: String,
    userProfileImageUrl: String,
    serviceTitle: String,
    serviceMinimumPriceCeiling: Int,
    priceUnit: String,
    serviceRating: Double,
    onClick: () -> Unit,
    isFavorite: Boolean? = null,
    onFavoriteClick: (() -> Unit)? = null,
    cardType: ServiceCardType = ServiceCardType.DEFAULT,
    modifier: Modifier = Modifier
) {
    val cardHeight = when (cardType) {
        ServiceCardType.DEFAULT -> 279.dp
        ServiceCardType.RESULT -> 437.dp
    }
    val imageHeight = when (cardType) {
        ServiceCardType.DEFAULT -> 175.dp
        ServiceCardType.RESULT -> 358.dp
    }
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = HustleColors.white,
            contentColor = HustleColors.primaryBlack
        ),
        border = BorderStroke(width = 1.dp, color = HustleColors.iconInactive),
        modifier = modifier.height(cardHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            // TODO: Add loading and error states
            SubcomposeAsyncImage(
                model = serviceImageUrl,
                contentDescription = "Service Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            if (isFavorite != null && onFavoriteClick != null) {
                FavoriteButton(
                    isFavorite = isFavorite,
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(HustleSpacing.small)
                        .size(32.dp)
                )
            }
        }

        when (cardType) {
            ServiceCardType.DEFAULT -> {
                DefaultServiceCardContent(
                    userName = userName,
                    userProfileImageUrl = userProfileImageUrl,
                    serviceTitle = serviceTitle,
                    serviceMinimumPriceCeiling = serviceMinimumPriceCeiling,
                    priceUnit = priceUnit,
                    serviceRating = serviceRating
                )
            }

            ServiceCardType.RESULT -> {
                ResultServiceCardContent(
                    userName = userName,
                    userProfileImageUrl = userProfileImageUrl,
                    serviceTitle = serviceTitle,
                    serviceMinimumPriceCeiling = serviceMinimumPriceCeiling,
                    priceUnit = priceUnit,
                    serviceRating = serviceRating
                )
            }
        }
    }
}

@Composable
private fun DefaultServiceCardContent(
    userName: String,
    userProfileImageUrl: String,
    serviceTitle: String,
    serviceMinimumPriceCeiling: Int,
    priceUnit: String,
    serviceRating: Double,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = HustleSpacing.extraSmall,
                bottom = HustleSpacing.medium,
                start = 14.dp,
                end = 14.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(HustleSpacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserProfilePicture(
                imageUrl = userProfileImageUrl,
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                lineHeight = 14.sp
            )
        }

        Text(
            text = serviceTitle,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 2,
            lineHeight = 14.sp,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "From \$$serviceMinimumPriceCeiling$priceUnit",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 106.dp)
            )
            ServiceRatingLabel(rating = serviceRating)
        }
    }
}

@Composable
private fun ResultServiceCardContent(
    userName: String,
    userProfileImageUrl: String,
    serviceTitle: String,
    serviceMinimumPriceCeiling: Int,
    priceUnit: String,
    serviceRating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = HustleSpacing.medium)
    ) {
        UserProfilePicture(
            imageUrl = userProfileImageUrl,
            modifier = Modifier.size(39.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .widthIn(max = 175.dp)
                .padding(start = HustleSpacing.medium)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                lineHeight = 14.sp
            )
            Text(
                text = serviceTitle,
                style = MaterialTheme.typography.labelLarge,
                color = HustleColors.secondaryGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            ServiceRatingLabel(
                rating = serviceRating, textStyle = MaterialTheme.typography.labelLarge.copy(
                    color = HustleColors.secondaryGray
                )
            )
            Text(
                text = "From \$$serviceMinimumPriceCeiling$priceUnit",
                style = MaterialTheme.typography.labelLarge,
                color = HustleColors.secondaryGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

data class ServiceCardPreviewParameters(
    val isFavorite: Boolean? = null,
    val onFavoriteClick: (() -> Unit)? = null,
    val cardType: ServiceCardType = ServiceCardType.DEFAULT,
    val modifier: Modifier = Modifier
)

class ServiceCardPreviewParameterProvider : PreviewParameterProvider<ServiceCardPreviewParameters> {
    override val values: Sequence<ServiceCardPreviewParameters>
        get() = sequenceOf(
            ServiceCardPreviewParameters(
                isFavorite = true,
                onFavoriteClick = {},
                modifier = Modifier.width(178.dp)
            ),
            ServiceCardPreviewParameters(
                isFavorite = false,
                onFavoriteClick = {},
                modifier = Modifier.width(178.dp)
            ),
            ServiceCardPreviewParameters(
                cardType = ServiceCardType.RESULT,
                modifier = Modifier.width(365.dp)
            )
        )
}

@Preview(showBackground = true)
@Composable
private fun ServiceCardPreview(
    @PreviewParameter(ServiceCardPreviewParameterProvider::class) parameters: ServiceCardPreviewParameters
) {
    val testService = TEST_SERVICES[0]
    HustleTheme {
        ServiceCard(
            serviceImageUrl = testService.displayImageUrl,
            userName = testService.user.displayName ?: "",
            userProfileImageUrl = testService.user.photoUrl ?: "",
            serviceTitle = testService.name,
            serviceMinimumPriceCeiling = ceil(testService.minimumPrice).toInt(),
            priceUnit = testService.priceUnit,
            serviceRating = testService.rating,
            onClick = {},
            isFavorite = parameters.isFavorite,
            onFavoriteClick = parameters.onFavoriteClick,
            cardType = parameters.cardType,
            modifier = parameters.modifier
        )
    }
}


