package com.gzaber.weatherapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.ui.util.toPreferences
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
                .catch { _uiState.update { it.copy(isError = true) } }
                .collect { weatherUnitsPreferences ->
                    _uiState.update {
                        it.copy(
                            weatherUnitsPreferences = weatherUnitsPreferences
                                .toWeatherUnits()
                                .toPreferences()
                        )
                    }
                }
        }
    }

    fun onTemperatureUnitSelected(temperatureUnit: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateTemperatureUnit(temperatureUnit)
        }
    }

    fun onWindSpeedUnitSelected(windSpeedUnit: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateWindSpeedUnit(windSpeedUnit)
        }
    }

    fun onPrecipitationUnitSelected(precipitationUnit: String) {
        viewModelScope.launch {
            userPreferencesRepository.updatePrecipitationUnit(precipitationUnit)
        }
    }
}