package com.gzaber.weatherapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.ui.settings.util.toSettingsDescription
import com.gzaber.weatherapp.ui.settings.util.toSettingsPrecipitationUnit
import com.gzaber.weatherapp.ui.settings.util.toSettingsTemperatureUnit
import com.gzaber.weatherapp.ui.settings.util.toSettingsWindSpeedUnit
import com.gzaber.weatherapp.ui.util.toWeatherUnits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.observeWeatherUnits()
                .catch { _uiState.update { it.copy(settingsDataState = SettingsDataState.Error) } }
                .collect { weatherUnitsPreferences ->
                    _uiState.update {
                        val weatherUnits = weatherUnitsPreferences.toWeatherUnits()
                        it.copy(
                            settingsDataState = SettingsDataState.Success(weatherUnits),
                            selectedTemperatureUnit = weatherUnits.temperatureUnit.toSettingsDescription(),
                            selectedWindSpeedUnit = weatherUnits.windSpeedUnit.toSettingsDescription(),
                            selectedPrecipitationUnit = weatherUnits.precipitationUnit.toSettingsDescription()
                        )
                    }
                }
        }
    }

    fun onTemperatureUnitSelected(description: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateTemperatureUnit(
                description.toSettingsTemperatureUnit().name
            )
        }
    }

    fun onWindSpeedUnitSelected(description: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateWindSpeedUnit(
                description.toSettingsWindSpeedUnit().name
            )
        }
    }

    fun onPrecipitationUnitSelected(description: String) {
        viewModelScope.launch {
            userPreferencesRepository.updatePrecipitationUnit(
                description.toSettingsPrecipitationUnit().name
            )
        }
    }
}
