package com.hvk.composegraph.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hvk.composegraph.DashboardTheme
import com.hvk.composegraph.TextSecondary
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChartTitleRow(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text( // "O OPTIONS" yerine basit metin
            text = "OPTIONS",
            style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
        )
    }
}


@Preview
@Composable
private fun Preview() {
   DashboardTheme {
       ChartTitleRow("Preview")
   }
}