package com.travelmarket.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = TravelMarketTeal80,
    onPrimary = Color.White,
    primaryContainer = TravelMarketTeal.copy(alpha = 0.3f),
    onPrimaryContainer = TravelMarketTeal80,
    
    secondary = TravelMarketOrange80,
    onSecondary = Color.White,
    secondaryContainer = TravelMarketOrange.copy(alpha = 0.3f),
    onSecondaryContainer = TravelMarketOrange80,
    
    tertiary = TravelMarketYellow80,
    onTertiary = Color.Black,
    tertiaryContainer = TravelMarketYellow.copy(alpha = 0.3f),
    onTertiaryContainer = TravelMarketYellow80,
    
    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFFE8E8E8),
    surface = Color(0xFF2A2A2A),
    onSurface = Color(0xFFE8E8E8),
    surfaceVariant = Color(0xFF3A3A3A),
    onSurfaceVariant = Color(0xFFB8B8B8),
    
    error = TravelMarketRed80,
    onError = Color.White,
    errorContainer = TravelMarketRed.copy(alpha = 0.3f),
    onErrorContainer = TravelMarketRed80,
    
    outline = TravelMarketMediumGray,
    outlineVariant = TravelMarketGray
)

private val LightColorScheme = lightColorScheme(
    primary = TravelMarketTeal,
    onPrimary = Color.White,
    primaryContainer = TravelMarketTeal.copy(alpha = 0.1f),
    onPrimaryContainer = TravelMarketTeal,
    
    secondary = TravelMarketOrange,
    onSecondary = Color.White,
    secondaryContainer = TravelMarketOrange.copy(alpha = 0.1f),
    onSecondaryContainer = TravelMarketOrange,
    
    tertiary = TravelMarketYellow,
    onTertiary = Color.Black,
    tertiaryContainer = TravelMarketYellow.copy(alpha = 0.1f),
    onTertiaryContainer = TravelMarketYellow,
    
    background = TravelMarketLightGray,
    onBackground = TravelMarketDarkGray,
    surface = Color.White,
    onSurface = TravelMarketDarkGray,
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = TravelMarketMediumGray,
    
    error = TravelMarketRed,
    onError = Color.White,
    errorContainer = TravelMarketRed.copy(alpha = 0.1f),
    onErrorContainer = TravelMarketRed,
    
    outline = TravelMarketGray,
    outlineVariant = TravelMarketLightGray
)

@Composable
fun TravelMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Deshabilitado para mantener colores personalizados
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
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}