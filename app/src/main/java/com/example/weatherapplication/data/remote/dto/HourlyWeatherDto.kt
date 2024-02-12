package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("time") val time: String,
    @SerialName("temp_c") val temperatureInCelsius: Float,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("wind_kph") val windSpeedInKilometersPerHour: Float,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDirection: String,
)
