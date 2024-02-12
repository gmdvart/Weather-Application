package com.example.weatherapplication.navigation

sealed class NavRoute(val route: String) {
    object Main : NavRoute(route = "main")
    object Forecast : NavRoute(route = "forecast")
}