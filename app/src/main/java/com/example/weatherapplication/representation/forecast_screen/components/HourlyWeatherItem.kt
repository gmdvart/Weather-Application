package com.example.weatherapplication.representation.forecast_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.model.HourlyWeather
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourlyWeather: HourlyWeather
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = hourlyWeather.getTimeInHours(),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(12.dp))

        AsyncImage(
            modifier = Modifier.size(52.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(true)
                .data("https:${hourlyWeather.conditionIconUrl}")
                .build(),
            contentDescription = hourlyWeather.conditionText
        )
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "${hourlyWeather.temperature.toInt()}°C",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.wind_speed_value, hourlyWeather.windSpeed.toInt()),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            modifier = Modifier
                .size(28.dp)
                .graphicsLayer {
                    transformOrigin = TransformOrigin.Center
                    rotationZ = -90f + hourlyWeather.windDegree
                },
            tint = MaterialTheme.colorScheme.onSurface,
            painter = painterResource(R.drawable.wind_arrow_alt),
            contentDescription = stringResource(R.string.wind_direction, hourlyWeather.windDirection)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyWeatherItemPreview() {
    val hourlyWeatherMock = HourlyWeather(
        time = LocalDateTime.parse("2024-02-01 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        temperature = 10f,
        conditionIconUrl = "Clear",
        conditionText = "Clear",
        windSpeed = 9f,
        windDegree = 251,
        windDirection = "SW"
    )

    WeatherApplicationTheme(darkTheme = true) {
        Surface {
            HourlyWeatherItem(
                modifier = Modifier.padding(8.dp),
                hourlyWeather = hourlyWeatherMock
            )
        }
    }
}