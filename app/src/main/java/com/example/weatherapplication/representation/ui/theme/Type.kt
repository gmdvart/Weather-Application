package com.example.weatherapplication.representation.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.R

val ralewayFontFamily = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_semibold, FontWeight.SemiBold)
)

// TODO("Implement font typography")

// Set of Material typography styles to start with
val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold
    ),
    headlineLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleSmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodySmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    )
)