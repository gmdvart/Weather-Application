package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.remote.WeatherService
import com.example.weatherapplication.data.remote.dto.WeatherDto
import com.example.weatherapplication.domain.repository.WeatherRemoteRepository
import com.example.weatherapplication.domain.utils.Resource
import io.ktor.client.plugins.*
import javax.inject.Inject

class WeatherRemoteRepositoryImpl @Inject constructor(private val weatherService: WeatherService): WeatherRemoteRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherDto> {
        return try {
            Resource.Success(data = weatherService.getWeatherForecast(latitude, longitude))
        } catch (e: RedirectResponseException) {
            Resource.Error(errorMessage = e.response.status.description)
        } catch (e: ServerResponseException) {
            Resource.Error(errorMessage = e.response.status.description)
        } catch (e: ClientRequestException) {
            Resource.Error(errorMessage = e.response.status.description)
        } catch (e: Exception) {
            Resource.Error(errorMessage = e.message)
        }
    }
}