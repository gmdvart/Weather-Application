package com.example.weatherapplication.data.remote

import com.example.weatherapplication.data.remote.dto.WeatherDto

interface WeatherService {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherDto

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/forecast.json"
        const val API_KEY = "key"
        const val KEY = "acdf9e109e3a47bfa5a151814230112"
        const val LOCATION_KEY = "q"
        const val DAYS_KEY = "days"
        const val DAYS_TO_FORECAST = 2
        const val AIR_QUALITY_KEY = "aqi"
        const val IS_AIR_QUALITY_DATA_INCLUDED = "yes"
    }
}