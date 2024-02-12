package com.example.weatherapplication.representation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.weather.WindInfo
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme

@Composable
fun WindDisplay(
    modifier: Modifier = Modifier,
    windInfo: WindInfo
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = windInfo.windDirection,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin.Center
                        rotationZ = -90f + windInfo.windDegree
                    },
                painter = painterResource(R.drawable.wind_arrow_alt),
                contentDescription = stringResource(R.string.wind_direction, windInfo.windDirection)
            )
            Text(
                text = stringResource(R.string.wind_speed_value, windInfo.windSpeed.toInt()),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WindDisplayPreview() {
    val windInfoMock = WindInfo(
        windSpeed = 24.1f,
        windDegree = 225f,
        windDirection = "SW"
    )

    WeatherApplicationTheme {
        WindDisplay(
            modifier = Modifier.padding(24.dp),
            windInfo = windInfoMock
        )
    }
}