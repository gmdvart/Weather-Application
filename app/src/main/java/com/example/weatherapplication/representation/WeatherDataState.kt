package com.example.weatherapplication.representation

import com.example.weatherapplication.domain.weather.WeatherData

data class WeatherDataState(
    val data: WeatherData? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = true
)
