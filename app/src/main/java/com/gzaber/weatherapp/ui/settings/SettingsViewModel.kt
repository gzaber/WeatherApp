package com.gzaber.weatherapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.ui.util.toWeatherUnits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            temperatureUnitOptions = TemperatureUnit.entries.map {
                convertTemperatureUnitToDescription(it)
            },
            windSpeedUnitOptions = WindSpeedUnit.entries.map {
                convertWindSpeedUnitToDescription(it)
            },
            precipitationUnitOptions = PrecipitationUnit.entries.map {
                convertPrecipitationUnitToDescription(it)
            }
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.observeWeatherUnits()
                .catch { _uiState.update { it.copy(isError = true) } }
                .collect { weatherUnitsPreferences ->
                    _uiState.update {
                        val weatherUnits = weatherUnitsPreferences.toWeatherUnits()
                        it.copy(
                            weatherUnits = weatherUnits,
                            selectedTemperatureUnit = convertTemperatureUnitToDescription(
                                weatherUnits.temperatureUnit
                            ),
                            selectedWindSpeedUnit = convertWindSpeedUnitToDescription(
                                weatherUnits.windSpeedUnit
                            ),
                            selectedPrecipitationUnit = convertPrecipitationUnitToDescription(
                                weatherUnits.precipitationUnit
                            )
                        )
                    }
                }
        }
    }

    fun onTemperatureUnitSelected(temperatureDescription: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateTemperatureUnit(
                convertDescriptionToTemperatureUnit(temperatureDescription).name
            )
        }
    }

    fun onWindSpeedUnitSelected(windSpeedDescription: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateWindSpeedUnit(
                convertDescriptionToWindSpeedUnit(windSpeedDescription).name
            )
        }
    }

    fun onPrecipitationUnitSelected(precipitationDescription: String) {
        viewModelScope.launch {
            userPreferencesRepository.updatePrecipitationUnit(
                convertDescriptionToPrecipitationUnit(precipitationDescription).name
            )
        }
    }

    private fun convertTemperatureUnitToDescription(temperatureUnit: TemperatureUnit): String {
        return when (temperatureUnit) {
            TemperatureUnit.CELSIUS -> "Celsius °C"
            TemperatureUnit.FAHRENHEIT -> "Fahrenheit °F"
        }
    }

    private fun convertWindSpeedUnitToDescription(windSpeedUnit: WindSpeedUnit): String {
        return when (windSpeedUnit) {
            WindSpeedUnit.KILOMETERS_PER_HOUR -> "km/h"
            WindSpeedUnit.METERS_PER_SECOND -> "m/s"
            WindSpeedUnit.MILES_PER_HOUR -> "mph"
            WindSpeedUnit.KNOTS -> "Knots"
        }
    }

    private fun convertPrecipitationUnitToDescription(precipitationUnit: PrecipitationUnit): String {
        return when (precipitationUnit) {
            PrecipitationUnit.MILLIMETER -> "Millimeter"
            PrecipitationUnit.INCH -> "Inch"
        }
    }

    private fun convertDescriptionToTemperatureUnit(temperatureDescription: String): TemperatureUnit {
        return when (temperatureDescription) {
            "Celsius °C" -> TemperatureUnit.CELSIUS
            else -> TemperatureUnit.FAHRENHEIT
        }
    }

    private fun convertDescriptionToWindSpeedUnit(windSpeedDescription: String): WindSpeedUnit {
        return when (windSpeedDescription) {
            "km/h" -> WindSpeedUnit.KILOMETERS_PER_HOUR
            "m/s" -> WindSpeedUnit.METERS_PER_SECOND
            "mph" -> WindSpeedUnit.MILES_PER_HOUR
            else -> WindSpeedUnit.KNOTS
        }
    }

    private fun convertDescriptionToPrecipitationUnit(precipitationDescription: String): PrecipitationUnit {
        return when (precipitationDescription) {
            "Millimeter" -> PrecipitationUnit.MILLIMETER
            else -> PrecipitationUnit.INCH
        }
    }
}
