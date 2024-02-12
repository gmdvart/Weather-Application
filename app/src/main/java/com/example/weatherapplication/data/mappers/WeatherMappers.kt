package com.example.weatherapplication.data.mappers

import com.example.weatherapplication.data.remote.dto.HourlyWeatherDto
import com.example.weatherapplication.data.remote.dto.WeatherDto
import com.example.weatherapplication.domain.model.HourlyWeather
import com.example.weatherapplication.domain.weather.*
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun WeatherDto.toSummaryInfo(): SummaryInfo {
    return SummaryInfo(
        location = "${location.city}, ${location.country}",
        date = LocalDateTime.parse(current.lastUpdated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        temperature = current.temperatureInCelsius,
        condition = current.condition.text,
        temperatureRange = "${forecast.forecastDays[0].day.minTemperatureInCelsius.toInt()}°" +
                " / " + "${forecast.forecastDays[0].day.maxTemperatureInCelsius.toInt()}°",
        windSpeed = current.windSpeedInKilometerPerHour,
        humidity = current.humidity,
        pressure = current.pressureInMilliBars
    )
}

fun HourlyWeatherDto.toHourlyWeather(): HourlyWeather {
    return HourlyWeather(
        time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        temperature = temperatureInCelsius,
        conditionIconUrl = condition.iconUrl,
        conditionText = condition.text,
        windSpeed = windSpeedInKilometersPerHour,
        windDegree = windDegree,
        windDirection = windDirection
    )
}

fun WeatherDto.toForecastInfo(): ForecastInfo {
    val result: MutableList<HourlyWeather> = mutableListOf()
    val currentTime = LocalDateTime.now()

    for (i in currentTime.hour..23)
        result.add(forecast.forecastDays[0].hourly[i].toHourlyWeather())

    val daysRemainsToForecast = 24 - (23 - currentTime.hour)

    for (i in 0.until(daysRemainsToForecast))
        result.add(forecast.forecastDays[1].hourly[i].toHourlyWeather())

    return ForecastInfo(forecast = result)
}

fun WeatherDto.toWindInfo(): WindInfo {
    return WindInfo(
        windSpeed = current.windSpeedInKilometerPerHour,
        windDegree = current.windDegree,
        windDirection = current.windDirection
    )
}

fun WeatherDto.toAstroInfo(): AstroInfo {
    return AstroInfo(
        sunrise = LocalTime.parse(forecast.forecastDays[0].astro.sunrise, DateTimeFormatter.ofPattern("KK:mm a")),
        sunset = LocalTime.parse(forecast.forecastDays[0].astro.sunset, DateTimeFormatter.ofPattern("KK:mm a")),
        moonPhase = forecast.forecastDays[0].astro.moonPhase
    )
}

fun WeatherDto.toCurrentDayInfo(): CurrentDayInfo {
    return CurrentDayInfo(
        realFeel = current.feelsLikeInCelsius,
        humidity = current.humidity,
        pressure = current.pressureInMilliBars,
        uvIndex = current.uv,
        chanceOfRain = forecast.forecastDays[0].day.dailyChanceOfRain
    )
}
