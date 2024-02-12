package com.example.weatherapplication.representation

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.weatherapplication.navigation.NavGraph
import com.example.weatherapplication.representation.main_screen.WeatherMainScreen

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    NavGraph(
        viewModel = viewModel,
        navController = rememberNavController()
    )
}
