package com.example.weatherapplication.representation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.domain.location.LocationTracker
import com.example.weatherapplication.domain.repository.WeatherDataRepository
import com.example.weatherapplication.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherDataState())
        private set

    fun loadWeatherData() {
        viewModelScope.launch {
            state = state.copy(
                errorMessage = null,
                isLoading = true
            )

            locationTracker.getCurrentLocation()?.let { location ->
                when (val resource = weatherDataRepository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            data = resource.data,
                            errorMessage = null,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            data = null,
                            errorMessage = resource.errorMessage,
                            isLoading = false
                        )
                    }
                }

            } ?: kotlin.run {
                state = state.copy(
                    errorMessage = "Couldn't retrieve a location. Make sure all permissions are granted and GPS is enabled.",
                    isLoading = false
                )
            }
        }
    }
}