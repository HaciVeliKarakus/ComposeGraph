// commonMain/kotlin/com/yourcompany/dashboard/ui/Charts.kt
package com.hvk.composegraph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.max

// --- Basitleştirilmiş Bar + Line Chart ---
@Composable
fun SimpleBarLineChart(
    barData: List<Float>,
    lineData: List<Float>,
    barColor: Color = MaterialTheme.colorScheme.primary,
    lineColor: Color = MaterialTheme.colorScheme.secondary,
    axisColor: Color = AxisColor,
    modifier: Modifier = Modifier
) {
    val maxBarValue = barData.maxOrNull() ?: 1f
    val maxLineValue = lineData.maxOrNull() ?: 1f
    val maxValue = max(maxBarValue, maxLineValue) // Genel Y ekseni için

    BoxWithConstraints(modifier = modifier) {
        val canvasWidth = constraints.maxWidth.toFloat()
        val canvasHeight = constraints.maxHeight.toFloat()
        val barWidth = canvasWidth / (barData.size * 1.5f) // Barlar arası boşluk için
        val horizontalPadding = barWidth * 0.25f

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Basit X ekseni çizgisi
            drawLine(
                color = axisColor,
                start = Offset(0f, canvasHeight),
                end = Offset(canvasWidth, canvasHeight),
                strokeWidth = 1.dp.toPx()
            )

            // Barları çiz
            barData.forEachIndexed { index, value ->
                val barHeight = (value / maxValue) * canvasHeight
                val left = index * (barWidth + horizontalPadding) + horizontalPadding / 2
                drawRect(
                    color = barColor,
                    topLeft = Offset(left, canvasHeight - barHeight),
                    size = Size(barWidth, barHeight)
                )
            }

            // Çizgiyi çiz
            if (lineData.size > 1) {
                val linePath = Path()
                val stepX = canvasWidth / (lineData.size - 1)
                lineData.forEachIndexed { index, value ->
                    val x = index * stepX
                    val y = canvasHeight - (value / maxValue) * canvasHeight
                    if (index == 0) {
                        linePath.moveTo(x, y)
                    } else {
                        linePath.lineTo(x, y)
                    }
                }
                drawPath(
                    path = linePath,
                    color = lineColor,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}

// --- Basitleştirilmiş Çoklu Çizgi Grafiği ---
@Composable
fun SimpleMultiLineChart(
    dataSets: List<List<Float>>,
    colors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        ChartYellow // Ek renk
    ),
    axisColor: Color = AxisColor,
    modifier: Modifier = Modifier
) {
    if (dataSets.isEmpty() || dataSets.any { it.isEmpty() }) return // Veri yoksa çizme

    val maxValue = dataSets.maxOfOrNull { it.maxOrNull() ?: 0f } ?: 1f
    val minValue = dataSets.minOfOrNull { it.minOrNull() ?: 0f } ?: 0f
    val valueRange = max(1f, maxValue - minValue) // 0'a bölünmeyi engelle

    BoxWithConstraints(modifier = modifier) {
        val canvasWidth = constraints.maxWidth.toFloat()
        val canvasHeight = constraints.maxHeight.toFloat()

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Basit X ve Y ekseni çizgileri
            drawLine(
                color = axisColor,
                start = Offset(0f, canvasHeight),
                end = Offset(canvasWidth, canvasHeight),
                strokeWidth = 1.dp.toPx()
            )
             drawLine( // Basit Y ekseni
                color = axisColor,
                start = Offset(0f, 0f),
                end = Offset(0f, canvasHeight),
                strokeWidth = 1.dp.toPx()
            )

            dataSets.forEachIndexed { setIndex, data ->
                if (data.size > 1) {
                    val path = Path()
                    val stepX = canvasWidth / (data.size - 1)
                    data.forEachIndexed { index, value ->
                        val x = index * stepX
                        // Y değerini normalize et (min/max'a göre)
                        val y = canvasHeight - ((value - minValue) / valueRange) * canvasHeight
                        if (index == 0) {
                            path.moveTo(x, y)
                        } else {
                            path.lineTo(x, y)
                        }
                    }
                    drawPath(
                        path = path,
                        color = colors.getOrElse(setIndex) { Color.Gray }, // Liste dışına taşmayı engelle
                        style = Stroke(width = 1.5.dp.toPx())
                    )
                }
            }
        }
    }
}