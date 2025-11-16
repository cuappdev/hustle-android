package com.cornellappdev.hustle.ui.components.general.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.ui.theme.HustleTheme

@Composable
fun ServiceRatingLabel(
    rating: Double,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rating_star),
            contentDescription = "Star Icon",
            tint = Color.Unspecified
        )
        Text(
            text = String.format("%.1f", rating),
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ServiceRatingLabelPreview() {
    HustleTheme {
        ServiceRatingLabel(rating = 4.8)
    }
}