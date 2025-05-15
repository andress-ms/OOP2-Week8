package com.amms.week8practice.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary      = tealMid,
    onPrimary    = Color.White,
    secondary    = tealLight,
    onSecondary  = Color.White,
    background   = Color.White,
    onBackground = black,
    surface      = Color.White,
    onSurface    = black
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}