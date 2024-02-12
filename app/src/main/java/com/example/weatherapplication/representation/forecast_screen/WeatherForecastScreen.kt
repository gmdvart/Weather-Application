package com.example.weatherapplication.representation.forecast_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.model.HourlyWeather
import com.example.weatherapplication.domain.weather.ForecastInfo
import com.example.weatherapplication.representation.forecast_screen.components.HourlyWeatherItem
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherForecastScreen(
    onBackClick: () -> Unit = {},
    forecastInfo: ForecastInfo
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.forecast_overview_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
        ) {
            items(items = forecastInfo.forecast) {
                HourlyWeatherItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    hourlyWeather = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherForecastScreenPreview() {
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

    WeatherApplicationTheme(darkTheme = true) {
        WeatherForecastScreen(forecastInfo = forecastInfoMock)
    }
}