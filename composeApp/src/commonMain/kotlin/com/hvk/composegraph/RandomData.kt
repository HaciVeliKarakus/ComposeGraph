// commonMain/kotlin/com/yourcompany/dashboard/data/RandomData.kt
package com.hvk.composegraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlin.random.Random

// Grafiklerdeki nokta sayısını belirleyelim
private const val BAR_CHART_POINTS = 18
private const val LINE_CHART_POINTS = 30
private const val MULTI_LINE_POINTS = 20

fun rememberRandomFloatList(count: Int, min: Float, max: Float): List<Float> {
    return List(count) { Random.nextFloat() * (max - min) + min }
}