package com.example.weatherapplication.domain.repository

import com.example.weatherapplication.domain.utils.Resource
import com.example.weatherapplication.domain.weather.WeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherData>
}