package com.example.weatherapplication.domain.weather

import java.time.LocalTime

data class AstroInfo(
    val sunrise: LocalTime,
    val sunset: LocalTime,
    val moonPhase: String
)
