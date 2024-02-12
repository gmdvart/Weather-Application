package com.example.weatherapplication.representation.main_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.weather.CurrentDayInfo
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme

@Composable
fun CurrentDayDisplay(
    modifier: Modifier = Modifier,
    currentDayInfo: CurrentDayInfo
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.real_feel),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "${currentDayInfo.realFeel.toInt()}°",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.pressure),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                fontWeight = MaterialTheme.typography.displaySmall.fontWeight
                            )
                        ) {
                            append(currentDayInfo.pressure.toInt().toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            )
                        ) {
                            append(stringResource(R.string.pressure_units))
                        }
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.humidity),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "${currentDayInfo.humidity}%",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.uv_index),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = currentDayInfo.uvIndex.toInt().toString(),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(R.string.chance_of_rain),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "${currentDayInfo.chanceOfRain}%",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentDayDisplayPreview() {
    val currentDayInfoMock = CurrentDayInfo(
        realFeel = 19f,
        humidity = 65,
        pressure = 1000f,
        uvIndex = 12f,
        chanceOfRain = 85
    )

    WeatherApplicationTheme {
        CurrentDayDisplay(
            modifier = Modifier.padding(24.dp),
            currentDayInfo = currentDayInfoMock
        )
    }
}