package com.example.weatherapplication.representation.main_screen.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.weather.SummaryInfo
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import com.example.weatherapplication.representation.ui.theme.ralewayFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun SummaryDisplay(
    modifier: Modifier = Modifier,
    summaryInfo: SummaryInfo
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = summaryInfo.location,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(128.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 26.dp, vertical = 12.dp),
                text = summaryInfo.date.format(DateTimeFormatter.ofPattern("EEE, dd LLL")),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${summaryInfo.temperature.toInt()}°",
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = 204.sp,
                fontFamily = ralewayFontFamily,
                letterSpacing = TextUnit(-10f, TextUnitType.Sp),
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = summaryInfo.condition,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.weight(1f),
                text = summaryInfo.temperatureRange,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(64.dp))

        Card(
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                SummaryStatItem(
                    modifier = Modifier.weight(1f),
                    summaryStat = SummaryStat(
                        statImage = R.drawable.wind_alt,
                        statDescription = R.string.wind_speed,
                        value = stringResource(R.string.wind_speed_value, summaryInfo.windSpeed.toInt())
                    )
                )
                SummaryStatItem(
                    modifier = Modifier.weight(1f),
                    summaryStat = SummaryStat(
                        statImage = R.drawable.humidity_alt,
                        statDescription = R.string.humidity,
                        value = "${summaryInfo.humidity}%"
                    )
                )
                SummaryStatItem(
                    modifier = Modifier.weight(1f),
                    summaryStat = SummaryStat(
                        statImage = R.drawable.thermometer_alt,
                        statDescription = R.string.pressure,
                        value = stringResource(R.string.pressure_value, summaryInfo.pressure.toInt())
                    )
                )
            }
        }
    }
}

@Composable
fun SummaryStatItem(
    modifier: Modifier = Modifier,
    summaryStat: SummaryStat
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            painter = painterResource(summaryStat.statImage),
            contentDescription = stringResource(summaryStat.statDescription)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = summaryStat.value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

data class SummaryStat(
    @DrawableRes val statImage: Int,
    @StringRes val statDescription: Int,
    val value: String
)

@Preview(showBackground = true)
@Composable
fun SummaryDisplayPreview() {
    val summaryInfoMock = SummaryInfo(
        location = "Khabarovsk, Russia",
        date = LocalDateTime.parse("2024-01-30 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        temperature = 12f,
        condition = "Partly cloudy",
        temperatureRange = "19° - 21°",
        windSpeed = 2f,
        humidity = 62,
        pressure = 1000f,
    )

    WeatherApplicationTheme {
        SummaryDisplay(
            modifier = Modifier.padding(24.dp),
            summaryInfo = summaryInfoMock
        )
    }
}