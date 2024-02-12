package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayDto(
    @SerialName("date") val date: String,
    @SerialName("day") val day: DayDto,
    @SerialName("astro") val astro: AstroDto,
    @SerialName("hour") val hourly: List<HourlyWeatherDto>
)
