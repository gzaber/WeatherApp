package com.gzaber.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.settings.SettingsRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.observeLocation()
                .catch { _uiState.update { it.copy(isError = true) } }
                .collect { location ->
                    _uiState.update { it.copy(location = location) }
                    getCurrentWeather()
                    getHourlyWeather()
                }
        }
    }

    fun onWeatherForecastTypeChanged(weatherForecastType: WeatherForecastType) {
        _uiState.update {
            it.copy(weatherForecastType = weatherForecastType)
        }
        if (weatherForecastType == WeatherForecastType.DAILY && _uiState.value.dailyWeather.daily.isEmpty())
            getDailyWeather()
    }

    private fun getCurrentWeather() {
        _uiState.update { it.copy(isLoadingCurrentWeather = true) }
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        currentWeather = weatherRepository.getCurrentWeather(
                            it.location.latitude,
                            it.location.longitude
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
                            it.location.latitude,
                            it.location.longitude
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
                            it.location.latitude,
                            it.location.longitude
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