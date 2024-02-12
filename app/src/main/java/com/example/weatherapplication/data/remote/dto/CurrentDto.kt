package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDto(
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val temperatureInCelsius: Float,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("wind_kph") val windSpeedInKilometerPerHour: Float,
    @SerialName("wind_degree") val windDegree: Float,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("pressure_mb") val pressureInMilliBars: Float,
    @SerialName("humidity") val humidity: Int,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Float,
    @SerialName("uv") val uv: Float,
)
