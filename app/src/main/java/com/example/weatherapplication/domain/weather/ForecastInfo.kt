package com.example.weatherapplication.domain.weather

import com.example.weatherapplication.domain.model.HourlyWeather

data class ForecastInfo(
    val forecast: List<HourlyWeather>
)
