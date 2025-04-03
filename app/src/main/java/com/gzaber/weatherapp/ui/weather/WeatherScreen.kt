package com.gzaber.weatherapp.ui.weather

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import com.gzaber.weatherapp.ui.weather.composable.WeatherContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToSearch: () -> Unit,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "${uiState.locationPreferences.name}, ${uiState.locationPreferences.country}",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings screen"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search screen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { contentPadding ->
        WeatherContent(
            contentPadding = contentPadding,
            currentWeather = uiState.currentWeather,
            hourlyWeather = uiState.hourlyWeather,
            dailyWeather = uiState.dailyWeather,
            weatherForecastType = uiState.weatherForecastType,
            onWeatherForecastTypeChanged = viewModel::onWeatherForecastTypeChanged
        )
    }
}