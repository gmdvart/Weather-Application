package com.example.weatherapplication.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class HourlyWeather(
    val time: LocalDateTime,
    val temperature: Float,
    val conditionIconUrl: String,
    val conditionText: String,
    val windSpeed: Float,
    val windDegree: Int,
    val windDirection: String
) {
    fun getTimeInHours(): String {
        val currentTime = LocalDateTime.now()
        val hourlyWeatherTime = time
        return if (
            currentTime.hour == hourlyWeatherTime.hour &&
            currentTime.dayOfYear == hourlyWeatherTime.dayOfYear
        ) "Now"
        else {
            time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }
}
