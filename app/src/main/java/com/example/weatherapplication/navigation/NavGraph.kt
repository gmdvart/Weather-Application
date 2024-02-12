package com.example.weatherapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapplication.representation.WeatherViewModel
import com.example.weatherapplication.representation.forecast_screen.WeatherForecastScreen
import com.example.weatherapplication.representation.main_screen.WeatherMainScreen

@Composable
fun NavGraph(
    viewModel: WeatherViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.route
    ) {
        composable(route = NavRoute.Main.route) {
            WeatherMainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = NavRoute.Forecast.route) {
            viewModel.state.data?.forecast?.let {
                WeatherForecastScreen(
                    onBackClick = { navController.navigateUp() },
                    forecastInfo = it
                )
            }
        }
    }
}