package com.gzaber.weatherapp.ui.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.gzaber.weatherapp.ui.settings.composable.SettingsContent
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
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
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { contentPadding ->
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
}