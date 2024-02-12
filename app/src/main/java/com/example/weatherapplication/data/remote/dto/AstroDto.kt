package com.example.weatherapplication.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstroDto(
    @SerialName("sunset") val sunset: String,
    @SerialName("sunrise") val sunrise: String,
    @SerialName("moon_phase") val moonPhase: String
)
