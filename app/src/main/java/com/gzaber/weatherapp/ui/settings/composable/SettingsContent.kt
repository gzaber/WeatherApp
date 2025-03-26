package com.gzaber.weatherapp.ui.settings.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            RadioButtonGroup(
                title = "Temperature unit",
                radioOptions = temperatureUnitOptions,
                selectedOption = selectedTemperatureUnit,
                onOptionSelected = onTemperatureUnitSelected
            )
            RadioButtonGroup(
                title = "Wind speed unit",
                radioOptions = windSpeedUnitOptions,
                selectedOption = selectedWindSpeedUnit,
                onOptionSelected = onWindSpeedUnitSelected
            )
            RadioButtonGroup(
                title = "Precipitation unit",
                radioOptions = precipitationUnitOptions,
                selectedOption = selectedPrecipitationUnit,
                onOptionSelected = onPrecipitationUnitSelected
            )
        }
    }
}