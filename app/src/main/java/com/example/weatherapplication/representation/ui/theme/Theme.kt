package com.example.weatherapplication.representation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = light_blue,
    onPrimary = dark_grey,
    background = light_blue,
    onBackground = dark_grey,
    surfaceVariant = dark_grey,
    onSurfaceVariant = light_blue,
    outline = dark_grey,
    surface = surface_light,
    onSurface = on_surface_light
)


private val DarkColors = darkColorScheme(
    primary = dark_grey,
    onPrimary = light_blue,
    background = dark_grey,
    onBackground = light_blue,
    surfaceVariant = light_blue,
    onSurfaceVariant = dark_grey,
    outline = light_blue,
    surface = surface_dark,
    onSurface = on_surface_dark
)

@Composable
fun WeatherApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> {
            DarkColors
        }
        else -> {
            LightColors
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}