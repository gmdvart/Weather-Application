package com.example.weatherapplication.domain.weather

data class CurrentDayInfo(
    val realFeel: Float,
    val humidity: Int,
    val pressure: Float,
    val uvIndex: Float,
    val chanceOfRain: Int
)
