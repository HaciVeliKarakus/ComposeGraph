// commonMain/kotlin/com/yourcompany/dashboard/ui/Theme.kt
package com.hvk.composegraph

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val DarkBackground = Color(0xFF1A1A2E) // Görsele benzer koyu bir renk
val CardBackground = Color(0xFF16213E)
val TextPrimary = Color(0xFFE0E0E0)
val TextSecondary = Color(0xFFA0A0A0)
val ChartBlue = Color(0xFF00BCD4)
val ChartPink = Color(0xFFE91E63)
val ChartGreen = Color(0xFF4CAF50)
val ChartYellow = Color(0xFFFFEB3B)
val AxisColor = Color(0xFF606075)

private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    surface = CardBackground,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    primary = ChartBlue,
    secondary = ChartPink,
    tertiary = ChartGreen
    // Diğer renkleri de tanımlayabilirsiniz
)

@Composable
fun DashboardTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = MaterialTheme.typography.copy( // Basit typografi ayarları
             titleLarge = TextStyle(
                fontFamily = FontFamily.SansSerif, // Platformun varsayılanını kullanır
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextPrimary
            ),
             titleMedium = TextStyle(
                 fontFamily = FontFamily.SansSerif,
                 fontWeight = FontWeight.Medium,
                 fontSize = 14.sp,
                 color = TextPrimary
             ),
            bodyMedium = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = TextSecondary
            ),
            labelSmall = TextStyle(
                 fontFamily = FontFamily.SansSerif,
                 fontWeight = FontWeight.Normal,
                 fontSize = 10.sp,
                 color = AxisColor
             )
            // ... diğer stiller
        ),
        content = content
    )
}