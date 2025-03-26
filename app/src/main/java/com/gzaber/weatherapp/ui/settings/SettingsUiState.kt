package com.gzaber.weatherapp.ui.settings

import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits

data class SettingsUiState(
    val weatherUnits: WeatherUnits = WeatherUnits(),
    val temperatureUnitOptions: List<String> = listOf(),
    val windSpeedUnitOptions: List<String> = listOf(),
    val precipitationUnitOptions: List<String> = listOf(),
    val selectedTemperatureUnit: String = "",
    val selectedWindSpeedUnit: String = "",
    val selectedPrecipitationUnit: String = "",
    val isError: Boolean = false
)
