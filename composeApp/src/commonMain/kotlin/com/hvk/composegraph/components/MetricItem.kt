package com.hvk.composegraph.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MetricItem(
    label: String,
    value: String,
    valueStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = valueStyle, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}