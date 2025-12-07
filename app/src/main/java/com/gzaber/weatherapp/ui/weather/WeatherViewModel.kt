package com.gzaber.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.userpreferences.model.isNotEmpty
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.ui.util.toWeatherUnits
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userPreferencesRepository.observeLocation(),
                userPreferencesRepository.observeWeatherUnits()
            ) { location, units ->
                Pair(location, units)
            }
                .catch { _uiState.update { it.copy(weatherDataState = WeatherDataState.Error) } }
                .collect { (location, units) ->
                    _uiState.update {
                        it.copy(
                            locationPreferences = location,
                            weatherUnitsPreferences = units
                        )
                    }
                    if (location.isNotEmpty()) {
                        getWeather()
                    }
                }
        }
    }

    private fun getWeather() {
        _uiState.update { it.copy(weatherDataState = WeatherDataState.Loading) }
        viewModelScope.launch {
            try {
                supervisorScope {
                    val currentState = _uiState.value
                    val currentWeatherDeferred = async {
                        weatherRepository.getCurrentWeather(
                            latitude = currentState.locationPreferences.latitude,
                            longitude = currentState.locationPreferences.longitude,
                            weatherUnits = currentState.weatherUnitsPreferences.toWeatherUnits()
                        )
                    }
                    val hourlyWeatherDeferred = async {
                        weatherRepository.getHourlyWeather(
                            latitude = currentState.locationPreferences.latitude,
                            longitude = currentState.locationPreferences.longitude,
                            temperatureUnit = currentState.weatherUnitsPreferences.toWeatherUnits().temperatureUnit
                        )
                    }
                    val dailyWeatherDeferred = async {
                        weatherRepository.getDailyWeather(
                            latitude = currentState.locationPreferences.latitude,
                            longitude = currentState.locationPreferences.longitude,
                            temperatureUnit = currentState.weatherUnitsPreferences.toWeatherUnits().temperatureUnit
                        )
                    }

                    _uiState.update {
                        it.copy(
                            weatherDataState = WeatherDataState.Success(
                                currentWeather = currentWeatherDeferred.await(),
                                hourlyWeather = hourlyWeatherDeferred.await(),
                                dailyWeather = dailyWeatherDeferred.await()
                            )
                        )
                    }
                }
            } catch (_: Throwable) {
                _uiState.update { it.copy(weatherDataState = WeatherDataState.Error) }
            }
        }
    }
}
