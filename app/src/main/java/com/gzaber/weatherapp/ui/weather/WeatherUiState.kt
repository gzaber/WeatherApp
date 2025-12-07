package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.util.defaultLocationPreferences
import com.gzaber.weatherapp.ui.util.defaultWeatherUnitsPreferences

sealed interface WeatherDataState {
    data class Success(
        val currentWeather: CurrentWeather,
        val hourlyWeather: HourlyWeather,
        val dailyWeather: DailyWeather
    ) : WeatherDataState

    object Error : WeatherDataState
    object Loading : WeatherDataState
}

data class WeatherUiState(
    val weatherDataState: WeatherDataState = WeatherDataState.Loading,
    val locationPreferences: LocationPreferences = defaultLocationPreferences(),
    val weatherUnitsPreferences: WeatherUnitsPreferences = defaultWeatherUnitsPreferences()
)
