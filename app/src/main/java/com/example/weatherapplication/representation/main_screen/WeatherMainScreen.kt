package com.example.weatherapplication.representation.main_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapplication.domain.model.HourlyWeather
import com.example.weatherapplication.domain.weather.*
import com.example.weatherapplication.navigation.NavRoute
import com.example.weatherapplication.representation.WeatherViewModel
import com.example.weatherapplication.representation.main_screen.components.*
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import com.example.weatherapplication.representation.ui.theme.dark_grey
import com.example.weatherapplication.representation.ui.theme.light_blue
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherMainScreen(
    navController: NavHostController,
    viewModel: WeatherViewModel
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (!viewModel.state.isLoading) {
            if (viewModel.state.errorMessage.isNullOrBlank()) MaterialTheme.colorScheme.primary
            else lightColorScheme().error
         } else {
            dark_grey
        },
        animationSpec = tween(1000),
        label = "backgroundColorAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        if (viewModel.state.isLoading) {
            LoadingDisplay()
        }
        viewModel.state.errorMessage?.let {
            ErrorDisplay(it)
        }
        viewModel.state.data?.let {
            SuccessDisplay(
                navHostController = navController,
                weatherData = it
            )
        }
    }
}

@Composable
fun LoadingDisplay() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = light_blue
        )
    }
}

@Composable
fun SuccessDisplay(
    navHostController: NavHostController,
    weatherData: WeatherData
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        weatherData.summary?.let {
            SummaryDisplay(
                modifier = Modifier.padding(36.dp),
                summaryInfo = it
            )
        }

        weatherData.forecast?.let {
            ForecastOverviewDisplay(
                modifier = Modifier.padding(horizontal = 36.dp, vertical = 24.dp),
                forecastInfo = it,
                onTitleClick = { navHostController.navigate(route = NavRoute.Forecast.route) }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 36.dp)
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            weatherData.astro?.let {
                AstroDiplay(
                    modifier = Modifier.weight(1f),
                    astroInfo = it
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            weatherData.wind?.let {
                WindDisplay(
                    windInfo = it
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        weatherData.currentDay?.let {
            CurrentDayDisplay(
                modifier = Modifier.padding(horizontal = 36.dp),
                currentDayInfo = it
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun ErrorDisplay(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.Center),
            text = errorMessage,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingDisplayPreview() {
    WeatherApplicationTheme {
        LoadingDisplay()
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessDisplayPreview() {
    val weatherDataMock = WeatherData(
        summary = SummaryInfo(
            location = "Khabarovsk, Russia",
            date = LocalDateTime.parse("2024-01-30 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            temperature = 20f,
            condition = "Partly cloudy",
            temperatureRange = "19° - 21°",
            windSpeed = 2f,
            humidity = 62,
            pressure = 1000f,
        ),
        forecast = ForecastInfo(
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
        ),
        astro = AstroInfo(
            sunrise = LocalTime.parse("07:41 AM", DateTimeFormatter.ofPattern("KK:mm a")),
            sunset = LocalTime.parse("04:47 PM", DateTimeFormatter.ofPattern("KK:mm a")),
            moonPhase = "Waning Gibbous"
        ),
        wind = WindInfo(
            windSpeed = 24.1f,
            windDegree = 225f,
            windDirection = "SW"
        ),
        currentDay = CurrentDayInfo(
            realFeel = 19f,
            humidity = 65,
            pressure = 1000f,
            uvIndex = 12f,
            chanceOfRain = 85
        )
    )

    WeatherApplicationTheme {
        SuccessDisplay(
            navHostController = rememberNavController(),
            weatherData = weatherDataMock
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDisplayPreview() {
    WeatherApplicationTheme {
        ErrorDisplay("An unexpected error occurred")
    }
}
