package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("name") val city: String,
    @SerialName("country") val country: String,
)
