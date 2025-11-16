package com.cornellappdev.hustle.ui.components.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.hustle.R
import com.cornellappdev.hustle.ui.theme.HustleColors
import com.cornellappdev.hustle.ui.theme.HustleSpacing
import com.cornellappdev.hustle.ui.theme.HustleTheme

@Composable
fun HustleButton(
    onClick: () -> Unit,
    text: String?,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(20.dp),
    border: BorderStroke? = null,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = HustleColors.hustleGreen,
        contentColor = HustleColors.white
    ),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = HustleSpacing.small,
        vertical = HustleSpacing.extraSmall
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        border = border,
        colors = buttonColors,
        contentPadding = contentPadding
    ) {
        leadingIcon?.let {
            leadingIcon()
            if (text != null) Spacer(modifier = Modifier.width(4.dp))
        }
        text?.let {
            Text(
                text = it,
                style = textStyle
            )
        }
        trailingIcon?.let {
            if (text != null) Spacer(modifier = Modifier.width(4.dp))
            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HustleButtonPreview() {
    HustleTheme {
        HustleButton(
            onClick = {},
            text = "Lessons",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_lessons),
                    contentDescription = "Lessons Icon"
                )
            }
        )
    }
}