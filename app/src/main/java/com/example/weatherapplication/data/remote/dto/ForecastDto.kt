package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    @SerialName("forecastday") val forecastDays: List<ForecastDayDto>
)
