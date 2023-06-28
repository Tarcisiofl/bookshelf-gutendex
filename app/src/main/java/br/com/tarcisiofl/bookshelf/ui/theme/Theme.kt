package br.com.tarcisiofl.bookshelf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkPrimaryColor,
    primaryVariant = DarkPrimaryVariantColor,
    secondary = DarkSecondaryColor,
    background = DarkBackgroundColor,
    onSurface = DarkOnSurfaceColor
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryVariantColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    onSurface = OnSurfaceColor

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val Colors.outlineColor: Color
    get() = if (!isLight) DarkOutlineColor else LightOutlineColor

@Composable
fun BookshelfTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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