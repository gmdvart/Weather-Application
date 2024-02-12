package com.example.weatherapplication.domain.weather

import java.time.LocalDateTime

data class SummaryInfo(
    val location: String,
    val date: LocalDateTime,
    val temperature: Float,
    val condition: String,
    val temperatureRange: String,
    val windSpeed: Float,
    val humidity: Int,
    val pressure: Float
)
