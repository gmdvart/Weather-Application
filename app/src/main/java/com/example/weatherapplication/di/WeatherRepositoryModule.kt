package com.example.weatherapplication.di

import com.example.weatherapplication.data.repository.WeatherDataRepositoryImpl
import com.example.weatherapplication.data.repository.WeatherRemoteRepositoryImpl
import com.example.weatherapplication.domain.repository.WeatherDataRepository
import com.example.weatherapplication.domain.repository.WeatherRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRemoteRepository(
        weatherRepositoryImpl: WeatherRemoteRepositoryImpl
    ): WeatherRemoteRepository

    @Binds
    @Singleton
    abstract fun bindWeatherDataRepository(
        weatherDataRepositoryImpl: WeatherDataRepositoryImpl
    ): WeatherDataRepository
}