import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hvk.composegraph.ChartBlue
import com.hvk.composegraph.ChartPink
import com.hvk.composegraph.DashboardTheme
import com.hvk.composegraph.SimpleBarLineChart
import com.hvk.composegraph.SimpleMultiLineChart
import com.hvk.composegraph.TextPrimary
import com.hvk.composegraph.components.ChartLegend
import com.hvk.composegraph.components.ChartTitleRow
import com.hvk.composegraph.components.DashboardCard
import com.hvk.composegraph.components.MetricItem
import com.hvk.composegraph.rememberRandomFloatList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen() {
    var loadTimeData by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 18,
                min = 1f,
                max = 15f
            )
        )
    }
    var bounceRateData1 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 30,
                min = 20f,
                max = 80f
            )
        )
    }
    var startRenderData by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 18,
                min = 0.5f,
                max = 5f
            )
        )
    }
    var bounceRateData2 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 30,
                min = 30f,
                max = 90f
            )
        )
    }

    var pageLoadMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 0.5f,
                max = 2.0f
            ).first()
        )
    }
    var pageViewsMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 1.0f,
                max = 5.0f
            ).first()
        )
    }
    var bounceRateMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 30f,
                max = 60f
            ).first()
        )
    }
    var sessionsMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 200f,
                max = 800f
            ).first()
        )
    }
    var sessionLengthMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 5f,
                max = 30f
            ).first()
        )
    }
    var pvsPerSessionMetric by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 1,
                min = 1.5f,
                max = 4.0f
            ).first()
        )
    }

    var multiLineData1 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 100f,
                max = 500f
            )
        )
    }
    var multiLineData2 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 20f,
                max = 80f
            )
        )
    }
    var multiLineData3 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 1f,
                max = 4f
            )
        )
    }

    var multiLineDataSessions1 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 200f,
                max = 500f
            )
        )
    }
    var multiLineDataSessions2 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 5f,
                max = 40f
            )
        )
    }
    var multiLineDataSessions3 by remember {
        mutableStateOf(
            rememberRandomFloatList(
                count = 20,
                min = 1f,
                max = 4f
            )
        )
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
    ) {
        val isWideScreen = maxWidth >= 600.dp

        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()).padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "USERS: LAST 7 DAYS USING MEDIAN",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                // Dropdown ikonu veya başka etkileşimler eklenebilir
            }

            Spacer(modifier = Modifier.height(16.dp))

            val loadTimeCardContent: @Composable ColumnScope.() -> Unit = {
                ChartTitleRow("LOAD TIME VS BOUNCE RATE")
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Median Page Load: ", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        String.format("%.3fs", loadTimeData.average()),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    Text("Bounce Rate: ", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        String.format("%.1f%%", bounceRateData1.average()),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                SimpleBarLineChart(
                    barData = loadTimeData,
                    lineData = bounceRateData1,
                    modifier = Modifier.fillMaxWidth().height(150.dp) // Sabit yükseklik verdik
                )
                ChartLegend(listOf("Page Load (LUX)" to ChartBlue, "Bounce Rate" to ChartPink))
            }

            val startRenderCardContent: @Composable ColumnScope.() -> Unit = {
                ChartTitleRow("START RENDER VS BOUNCE RATE")
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Median Start Render: ", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        String.format("%.3fs", startRenderData.average()),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    // Bounce rate metriği eklenebilir
                }
                Spacer(modifier = Modifier.height(8.dp))
                SimpleBarLineChart(
                    barData = startRenderData,
                    lineData = bounceRateData2,
                    modifier = Modifier.fillMaxWidth().height(150.dp)
                )
                ChartLegend(listOf("Start Render (LUX)" to ChartBlue, "Bounce Rate" to ChartPink))
            }

            val pageViewsCardContent: @Composable ColumnScope.() -> Unit = {
                ChartTitleRow("PAGE VIEWS VS ONLOAD")
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MetricItem("Page Load", String.format("%.1fs", pageLoadMetric))
                    MetricItem("Page Views", String.format("%.1fMpvs", pageViewsMetric))
                    MetricItem("Bounce Rate", String.format("%.1f%%", bounceRateMetric))
                }
                Spacer(modifier = Modifier.height(12.dp))
                SimpleMultiLineChart(
                    dataSets = listOf(multiLineData1, multiLineData2, multiLineData3),
                    modifier = Modifier.fillMaxWidth().height(100.dp) // Sabit yükseklik
                )
            }

            val sessionsCardContent: @Composable ColumnScope.() -> Unit = {
                ChartTitleRow("SESSIONS")
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MetricItem("Sessions", String.format("%.0fK", sessionsMetric))
                    MetricItem("Session Length", String.format("%.0fmin", sessionLengthMetric))
                    MetricItem("PVs/Session", String.format("%.1fpvs", pvsPerSessionMetric))
                }
                Spacer(modifier = Modifier.height(12.dp))
                SimpleMultiLineChart(
                    dataSets = listOf(
                        multiLineDataSessions1,
                        multiLineDataSessions2,
                        multiLineDataSessions3
                    ),
                    modifier = Modifier.fillMaxWidth().height(100.dp) // Sabit yükseklik
                )
            }

            if (isWideScreen) {
                // Geniş Ekran Düzeni: 2'li Row'lar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DashboardCard(modifier = Modifier.weight(1f), content = loadTimeCardContent)
                    DashboardCard(modifier = Modifier.weight(1f), content = startRenderCardContent)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DashboardCard(modifier = Modifier.weight(1f), content = pageViewsCardContent)
                    DashboardCard(modifier = Modifier.weight(1f), content = sessionsCardContent)
                    // Eğer daha fazla kart olsaydı buraya eklenirdi
                    // Box(modifier = Modifier.weight(1f)) {} // Boşluk bırakmak için
                }
            } else {
                // Dar Ekran Düzeni: Tekli Column
                DashboardCard(modifier = Modifier.fillMaxWidth(), content = loadTimeCardContent)
                Spacer(modifier = Modifier.height(16.dp))
                DashboardCard(modifier = Modifier.fillMaxWidth(), content = startRenderCardContent)
                Spacer(modifier = Modifier.height(16.dp))
                DashboardCard(modifier = Modifier.fillMaxWidth(), content = pageViewsCardContent)
                Spacer(modifier = Modifier.height(16.dp))
                DashboardCard(modifier = Modifier.fillMaxWidth(), content = sessionsCardContent)
                // Eğer daha fazla kart olsaydı buraya eklenirdi
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                loadTimeData =
                    rememberRandomFloatList(count = 18, min = 1f, max = 15f)
                bounceRateData1 =
                    rememberRandomFloatList(count = 30, min = 20f, max = 80f)
                startRenderData =
                    rememberRandomFloatList(count = 18, min = 0.5f, max = 5f)
                bounceRateData2 =
                    rememberRandomFloatList(count = 30, min = 30f, max = 90f)

                pageLoadMetric = rememberRandomFloatList(count = 1, min = 0.5f, max = 2.0f).first()
                pageViewsMetric = rememberRandomFloatList(count = 1, min = 1.0f, max = 5.0f).first()
                bounceRateMetric = rememberRandomFloatList(count = 1, min = 30f, max = 60f).first()
                sessionsMetric = rememberRandomFloatList(count = 1, min = 200f, max = 800f).first()
                sessionLengthMetric =
                    rememberRandomFloatList(count = 1, min = 5f, max = 30f).first()
                pvsPerSessionMetric =
                    rememberRandomFloatList(count = 1, min = 1.5f, max = 4.0f).first()

                multiLineData1 =
                    rememberRandomFloatList(count = 20, min = 100f, max = 500f)
                multiLineData2 =
                    rememberRandomFloatList(count = 20, min = 20f, max = 80f)
                multiLineData3 =
                    rememberRandomFloatList(count = 20, min = 1f, max = 4f)

                multiLineDataSessions1 =
                    rememberRandomFloatList(count = 20, min = 200f, max = 500f)
                multiLineDataSessions2 =
                    rememberRandomFloatList(count = 20, min = 5f, max = 40f)
                multiLineDataSessions3 =
                    rememberRandomFloatList(count = 20, min = 1f, max = 4f)
            }) {
                Text("Generate Random Data")
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    DashboardTheme {
        DashboardScreen()
    }
}