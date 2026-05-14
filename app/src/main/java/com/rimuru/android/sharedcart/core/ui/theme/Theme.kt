package com.rimuru.android.sharedcart.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(

    primary = MainBlue,
    secondary = SecondaryBlue,

    background = LightBackground,
    surface = LightSurface,
    surfaceVariant = BlueContainer,

    primaryContainer = BlueContainerDark,

    onPrimary = White,
    onSecondary = White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,

    outline = BorderLight,

    error = ErrorRed
)

private val DarkColorScheme = darkColorScheme(

    primary = DarkPrimary,
    secondary = DarkSecondary,

    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = DarkContainer,

    primaryContainer = DarkContainer,

    onPrimary = DarkBackground,
    onSecondary = White,

    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,

    outline = DarkBorder,

    error = ErrorRed
)


@Composable
fun SharedCartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}