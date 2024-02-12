package com.example.weatherapplication.representation.main_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.weather.AstroInfo
import com.example.weatherapplication.representation.ui.theme.WeatherApplicationTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AstroDiplay(
    modifier: Modifier = Modifier,
    astroInfo: AstroInfo
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Text(
                    text = astroInfo.sunrise.format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.sunrise),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Row {
                Text(
                    text = astroInfo.sunset.format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.sunset),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = astroInfo.moonPhase,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AstroDiplayPreview() {
    val astroInfoMock = AstroInfo(
        sunrise = LocalTime.parse("07:41 AM", DateTimeFormatter.ofPattern("KK:mm a")),
        sunset = LocalTime.parse("04:47 PM", DateTimeFormatter.ofPattern("KK:mm a")),
        moonPhase = "Waning Gibbous"
    )

    WeatherApplicationTheme {
        AstroDiplay(
            modifier = Modifier.padding(24.dp),
            astroInfo = astroInfoMock
        )
    }
}