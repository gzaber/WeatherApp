package com.gzaber.weatherapp.ui.settings

import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.ui.util.emptyWeatherUnitsPreferences

data class SettingsUiState(
    val weatherUnitsPreferences: WeatherUnitsPreferences = emptyWeatherUnitsPreferences(),
    val isError: Boolean = false
)
