package com.gzaber.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.ui.util.toWeatherUnits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.observeLocation()
                .catch { _uiState.update { it.copy(isError = true) } }
                .collect { locationPreferences ->
                    _uiState.update { it.copy(locationPreferences = locationPreferences) }
                    getWeather()
                }
        }

        viewModelScope.launch {
            userPreferencesRepository.observeWeatherUnits()
                .catch { _uiState.update { it.copy(isError = true) } }
                .collect { weatherUnitsPreferences ->
                    _uiState.update { it.copy(weatherUnitsPreferences = weatherUnitsPreferences) }
                    getWeather()
                }
        }
    }

    fun onWeatherForecastTypeChanged(weatherForecastType: WeatherForecastType) {
        _uiState.update {
            it.copy(weatherForecastType = weatherForecastType)
        }
    }

    private fun getWeather() {
        getCurrentWeather()
        getHourlyWeather()
        getDailyWeather()
    }

    private fun getCurrentWeather() {
        _uiState.update { it.copy(isLoadingCurrentWeather = true) }
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        currentWeather = weatherRepository.getCurrentWeather(
                            it.locationPreferences.latitude,
                            it.locationPreferences.longitude,
                            it.weatherUnitsPreferences.toWeatherUnits()
                        ),
                        isLoadingCurrentWeather = false
                    )
                }
            } catch (_: Throwable) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    private fun getDailyWeather() {
        _uiState.update { it.copy(isLoadingDailyWeather = true) }
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        dailyWeather = weatherRepository.getDailyWeather(
                            it.locationPreferences.latitude,
                            it.locationPreferences.longitude,
                            it.weatherUnitsPreferences.toWeatherUnits()
                        ),
                        isLoadingDailyWeather = false
                    )
                }
            } catch (_: Throwable) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    private fun getHourlyWeather() {
        _uiState.update { it.copy(isLoadingHourlyWeather = true) }
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        hourlyWeather = weatherRepository.getHourlyWeather(
                            it.locationPreferences.latitude,
                            it.locationPreferences.longitude,
                            it.weatherUnitsPreferences.toWeatherUnits()
                        ),
                        isLoadingHourlyWeather = false
                    )
                }
            } catch (_: Throwable) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }
}