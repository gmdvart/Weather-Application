package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("location") val location: LocationDto,
    @SerialName("current") val current: CurrentDto,
    @SerialName("forecast") val forecast: ForecastDto
)
