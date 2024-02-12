package com.example.weatherapplication.domain.repository

import com.example.weatherapplication.data.remote.dto.WeatherDto
import com.example.weatherapplication.domain.utils.Resource

interface WeatherRemoteRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherDto>
}