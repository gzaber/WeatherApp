package com.gzaber.weatherapp.ui.weather

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.ui.util.composable.LoadingIndicator
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
                    if (uiState.isLoadingCurrentWeather.not() ||
                        uiState.isLoadingHourlyWeather.not() ||
                        uiState.isLoadingDailyWeather.not()
                    ) {
                        Text(
                            text = "${uiState.locationPreferences.name}, ${uiState.locationPreferences.country}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            painter = painterResource(R.drawable.ic_settings),
                            contentDescription = "Settings screen"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search screen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { contentPadding ->
        if (uiState.isLoadingCurrentWeather || uiState.isLoadingHourlyWeather || uiState.isLoadingDailyWeather) {
            LoadingIndicator(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding
            )
        } else {
            WeatherContent(
                contentPadding = contentPadding,
                currentWeather = uiState.currentWeather,
                hourlyWeather = uiState.hourlyWeather,
                dailyWeather = uiState.dailyWeather
            )
        }
    }
}