package com.example.weatherapplication.data.remote

import com.example.weatherapplication.data.remote.dto.WeatherDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class WeatherServiceImpl(private val client: HttpClient): WeatherService {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherDto {
        return client.get(WeatherService.BASE_URL) {
            parameter(WeatherService.API_KEY, WeatherService.KEY)
            parameter(WeatherService.LOCATION_KEY, "$latitude,$longitude")
            parameter(WeatherService.DAYS_KEY, WeatherService.DAYS_TO_FORECAST)
            parameter(WeatherService.AIR_QUALITY_KEY, WeatherService.IS_AIR_QUALITY_DATA_INCLUDED)
        }.body()
    }
}