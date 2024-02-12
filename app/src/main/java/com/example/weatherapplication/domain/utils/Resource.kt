package com.example.weatherapplication.domain.utils

sealed class Resource<T> {
    data class Success<T>(
        val data: T? = null
    ): Resource<T>()
    data class Error<T>(
        val errorMessage: String? = null,
        val defaultData: T? = null
    ): Resource<T>()
}
