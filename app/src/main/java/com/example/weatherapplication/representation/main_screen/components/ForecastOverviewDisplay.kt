package com.example.weatherapplication.representation.main_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.model.HourlyWeather
import com.example.weatherapplication.domain.weather.ForecastInfo
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ForecastOverviewDisplay(
    modifier: Modifier = Modifier,
    forecastInfo: ForecastInfo,
    onTitleClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onTitleClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.forecast_overview_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                painter = painterResource(R.drawable.arrow_forward),
                contentDescription = stringResource(R.string.forecast_overview_title)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(3) {
                HourlyForecastOverviewItem(
                    modifier = Modifier.weight(1f),
                    hourlyWeather = forecastInfo.forecast[it]
                )

                if (it == 0 || it == 1) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

@Composable
fun HourlyForecastOverviewItem(
    modifier: Modifier = Modifier,
    hourlyWeather: HourlyWeather
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${hourlyWeather.temperature.toInt()}°C",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            AsyncImage(
                modifier = Modifier.size(52.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .crossfade(true)
                    .data("https:${hourlyWeather.conditionIconUrl}")
                    .build(),
                contentDescription = hourlyWeather.conditionText
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = hourlyWeather.getTimeInHours(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastOverviewDisplayPreview() {
    val forecastInfoMock = ForecastInfo(
        forecast = List(9) {
            HourlyWeather(
                time = LocalDateTime.parse("2024-02-01 0$it:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                temperature = 10f + it,
                conditionIconUrl = "Clear",
                conditionText = "Clear",
                windSpeed = it.toFloat(),
                windDegree = 251,
                windDirection = "SW"
            )
        }
    )

    WeatherApplicationTheme {
        ForecastOverviewDisplay(
            modifier = Modifier.padding(24.dp),
            forecastInfo = forecastInfoMock
        )
    }
}