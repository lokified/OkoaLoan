package com.loki.okoaloan.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = CyberYellow,
    primaryVariant = Aureolin,
    secondary = Aureolin,
    surface = Jet,
    background = EerieBlack,
    onBackground = LightWhite,
    onSurface = LightWhite
)

private val LightColorPalette = lightColors(
    primary = CyberYellow,
    primaryVariant = Aureolin,
    secondary = Aureolin,
    surface = SilverChalice,
    background = LightWhite,
)

@Composable
fun OkoaLoanTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}