package com.cornellappdev.hustle.ui.components.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.ui.theme.HustleTheme

@Composable
fun ClickableSectionHeader(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = textStyle
        )
        Icon(
            painter = painterResource(R.drawable.ic_chevron),
            contentDescription = "Chevron Icon",
            tint = Color.Unspecified,
            modifier = Modifier.rotate(180f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ClickableSectionHeaderPreview() {
    HustleTheme {
        ClickableSectionHeader(
            title = "Popular right now",
            onClick = {}
        )
    }
}