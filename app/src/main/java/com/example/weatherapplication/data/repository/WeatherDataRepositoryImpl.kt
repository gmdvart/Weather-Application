package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.mappers.*
import com.example.weatherapplication.domain.repository.WeatherDataRepository
import com.example.weatherapplication.domain.repository.WeatherRemoteRepository
import com.example.weatherapplication.domain.utils.Resource
import com.example.weatherapplication.domain.weather.WeatherData
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    private val remoteRepository: WeatherRemoteRepository
) : WeatherDataRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherData> {
        return when (val resource = remoteRepository.getWeatherData(latitude, longitude)) {
            is Resource.Success -> {
                val weatherData = WeatherData(
                    summary = resource.data?.toSummaryInfo(),
                    forecast = resource.data?.toForecastInfo(),
                    astro = resource.data?.toAstroInfo(),
                    wind = resource.data?.toWindInfo(),
                    currentDay = resource.data?.toCurrentDayInfo(),
                )
                Resource.Success(data = weatherData)
            }
            is Resource.Error -> {
                Resource.Error(errorMessage = resource.errorMessage)
            }
        }
    }
}