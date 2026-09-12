package com.aiinterviewtrainer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Blue800,
    onPrimary = Color.White,
    primaryContainer = Blue100,
    onPrimaryContainer = Blue900,
    secondary = AppGreenLight,
    onSecondary = Color.White,
    tertiary = AppGold,
    background = AppBg,
    onBackground = AppText,
    surface = Color.White,
    onSurface = AppText,
    error = AppRed,
    onError = Color.White,
    outline = AppBorder
)

@Composable
fun AIInterviewTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}