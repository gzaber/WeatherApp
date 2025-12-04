package com.gzaber.weatherapp.ui.settings

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
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.ui.settings.composable.SettingsContent
import com.gzaber.weatherapp.ui.util.composable.ErrorSnackbar
import com.gzaber.weatherapp.ui.util.composable.LoadingIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val settingsDataState = uiState.settingsDataState
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(settingsDataState) {
        if (settingsDataState is SettingsDataState.Error) {
            snackbarHostState.showSnackbar("Unable to load settings")
        }
    }

    Scaffold(
        snackbarHost = { ErrorSnackbar(snackbarHostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { contentPadding ->
        when (settingsDataState) {
            is SettingsDataState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = contentPadding
                )
            }
            is SettingsDataState.Success -> {
                SettingsContent(
                    contentPadding = contentPadding,
                    temperatureUnitOptions = uiState.temperatureUnitOptions,
                    windSpeedUnitOptions = uiState.windSpeedUnitOptions,
                    precipitationUnitOptions = uiState.precipitationUnitOptions,
                    selectedTemperatureUnit = uiState.selectedTemperatureUnit,
                    selectedWindSpeedUnit = uiState.selectedWindSpeedUnit,
                    selectedPrecipitationUnit = uiState.selectedPrecipitationUnit,
                    onTemperatureUnitSelected = viewModel::onTemperatureUnitSelected,
                    onWindSpeedUnitSelected = viewModel::onWindSpeedUnitSelected,
                    onPrecipitationUnitSelected = viewModel::onPrecipitationUnitSelected
                )
            }
            is SettingsDataState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Unable to load settings")
                }
            }
        }
    }
}
