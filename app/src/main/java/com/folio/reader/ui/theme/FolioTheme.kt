package com.folio.reader.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF316452),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0EBDE),
    onPrimaryContainer = Color(0xFF1D2923),
    background = Color(0xFFF6F4ED),
    surface = Color(0xFFFFFEFA),
    surfaceVariant = Color(0xFFECEFE6),
    onSurface = Color(0xFF1D2923),
    onSurfaceVariant = Color(0xFF69766B),
    outline = Color(0xFFDCE1D7)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFD7EF72),
    onPrimary = Color(0xFF172015),
    primaryContainer = Color(0xFF303D24),
    onPrimaryContainer = Color(0xFFEAF5C9),
    background = Color(0xFF101511),
    surface = Color(0xFF1A211C),
    surfaceVariant = Color(0xFF252E25),
    onSurface = Color(0xFFF0F3E9),
    onSurfaceVariant = Color(0xFFB2BDAF),
    outline = Color(0xFF344137)
)

@Composable
fun FolioTheme(
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkMode) DarkColors else LightColors,
        content = content
    )
}

object FolioColors {
    val Lime = Color(0xFFD7EF72)
    val LightGreen = Color(0xFFE0EBDE)
    val DeepGreen = Color(0xFF204A3D)
    val ReaderQuote = Color(0xFFE4F0B9)
    val DarkReaderQuote = Color(0xFF394629)
}
