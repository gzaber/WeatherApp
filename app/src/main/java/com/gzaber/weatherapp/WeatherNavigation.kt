package com.gzaber.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gzaber.weatherapp.ui.search.SearchScreen
import com.gzaber.weatherapp.ui.weather.WeatherScreen
import kotlinx.serialization.Serializable

@Serializable
object Weather

@Serializable
object Search

@Composable
fun WeatherNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Weather,
        modifier = modifier
    ) {
        composable<Weather> {
            WeatherScreen(
                onNavigateToSearch = { navController.navigate(route = Search) }
            )
        }

        composable<Search> {
            SearchScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}