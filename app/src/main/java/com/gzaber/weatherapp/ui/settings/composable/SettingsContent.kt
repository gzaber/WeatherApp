package com.gzaber.weatherapp.ui.settings.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingsContent(
    contentPadding: PaddingValues,
    temperatureUnitOptions: List<String>,
    windSpeedUnitOptions: List<String>,
    precipitationUnitOptions: List<String>,
    selectedTemperatureUnit: String,
    selectedWindSpeedUnit: String,
    selectedPrecipitationUnit: String,
    onTemperatureUnitSelected: (String) -> Unit,
    onWindSpeedUnitSelected: (String) -> Unit,
    onPrecipitationUnitSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioButtonGroup(
            radioOptions = temperatureUnitOptions,
            selectedOption = selectedTemperatureUnit,
            onOptionSelected = onTemperatureUnitSelected
        )
        HorizontalDivider()
        RadioButtonGroup(
            radioOptions = windSpeedUnitOptions,
            selectedOption = selectedWindSpeedUnit,
            onOptionSelected = onWindSpeedUnitSelected
        )
        HorizontalDivider()
        RadioButtonGroup(
            radioOptions = precipitationUnitOptions,
            selectedOption = selectedPrecipitationUnit,
            onOptionSelected = onPrecipitationUnitSelected
        )
    }
}