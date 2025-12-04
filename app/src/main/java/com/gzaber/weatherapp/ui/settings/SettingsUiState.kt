package com.gzaber.weatherapp.ui.settings

import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.ui.settings.util.toSettingsDescription

sealed interface SettingsDataState {
    data class Success(val weatherUnits: WeatherUnits) : SettingsDataState
    object Error : SettingsDataState
    object Loading : SettingsDataState
}

data class SettingsUiState(
    val settingsDataState: SettingsDataState = SettingsDataState.Loading,
    val temperatureUnitOptions: List<String> = TemperatureUnit.entries
        .filter { it.toSettingsDescription().isNotBlank() }
        .map { it.toSettingsDescription() },
    val windSpeedUnitOptions: List<String> = WindSpeedUnit.entries
        .filter { it.toSettingsDescription().isNotBlank() }
        .map { it.toSettingsDescription() },
    val precipitationUnitOptions: List<String> = PrecipitationUnit.entries
        .filter { it.toSettingsDescription().isNotBlank() }
        .map { it.toSettingsDescription() },
    val selectedTemperatureUnit: String = "",
    val selectedWindSpeedUnit: String = "",
    val selectedPrecipitationUnit: String = ""
)
