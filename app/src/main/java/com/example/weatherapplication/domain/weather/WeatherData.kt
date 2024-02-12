package com.example.weatherapplication.domain.weather

data class WeatherData(
    val summary: SummaryInfo? = null,
    val forecast: ForecastInfo? = null,
    val astro: AstroInfo? = null,
    val wind: WindInfo? = null,
    val currentDay: CurrentDayInfo? = null
)
