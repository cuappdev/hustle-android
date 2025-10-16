package com.cornellappdev.hustle.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cornellappdev.hustle.R

val InstrumentSans = FontFamily(
    Font(R.font.instrument_sans_regular, FontWeight.Normal),
    Font(R.font.instrument_sans_medium, FontWeight.Medium),
    Font(R.font.instrument_sans_bold_italic, FontWeight.Bold, FontStyle.Italic)
)

val HelveticaNeue = FontFamily(
    Font(R.font.helvetica_neue_regular, FontWeight.Normal),
    Font(R.font.helvetica_neue_medium, FontWeight.Medium),
    Font(R.font.helvetica_neue_bold, FontWeight.Bold)
)
val HustleTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = InstrumentSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
)