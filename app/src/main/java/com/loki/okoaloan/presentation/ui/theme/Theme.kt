package com.loki.okoaloan.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = CyberYellow,
    primaryContainer = Aureolin,
    secondary = Aureolin,
    surface = Jet,
    background = EerieBlack,
    onBackground = LightWhite,
    onSurface = LightWhite
)

private val LightColorPalette = lightColorScheme(
    primary = CyberYellow,
    primaryContainer = Aureolin,
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
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
