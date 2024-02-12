package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("mintemp_c") val minTemperatureInCelsius: Float,
    @SerialName("maxtemp_c") val maxTemperatureInCelsius: Float,
    @SerialName("daily_chance_of_rain") val dailyChanceOfRain: Int
)
