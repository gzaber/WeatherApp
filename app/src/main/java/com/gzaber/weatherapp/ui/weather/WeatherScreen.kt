package com.gzaber.weatherapp.ui.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.ui.util.composable.ErrorSnackbar
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
    val weatherDataState = uiState.weatherDataState
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(weatherDataState) {
        if (weatherDataState is WeatherDataState.Error) {
            snackbarHostState.showSnackbar("Unable to fetch weather data")
        }
    }

    Scaffold(
        snackbarHost = { ErrorSnackbar(snackbarHostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if (weatherDataState is WeatherDataState.Success) {
                        Text(
                            text = "${uiState.locationPreferences.name}, ${uiState.locationPreferences.country}",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
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
        when (weatherDataState) {
            is WeatherDataState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = contentPadding
                )
            }
            is WeatherDataState.Success -> {
                WeatherContent(
                    contentPadding = contentPadding,
                    currentWeather = weatherDataState.currentWeather,
                    hourlyWeather = weatherDataState.hourlyWeather,
                    dailyWeather = weatherDataState.dailyWeather
                )
            }
            is WeatherDataState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Unable to fetch weather data")
                }
            }
        }
    }
}
