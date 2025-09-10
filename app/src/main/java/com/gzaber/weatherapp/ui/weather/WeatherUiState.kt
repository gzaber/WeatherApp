package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.util.defaultCurrentWeather
import com.gzaber.weatherapp.ui.util.defaultDailyWeather
import com.gzaber.weatherapp.ui.util.defaultHourlyWeather
import com.gzaber.weatherapp.ui.util.defaultLocationPreferences
import com.gzaber.weatherapp.ui.util.defaultWeatherUnitsPreferences

data class WeatherUiState(
    val locationPreferences: LocationPreferences = defaultLocationPreferences(),
    val weatherUnitsPreferences: WeatherUnitsPreferences = defaultWeatherUnitsPreferences(),
    val currentWeather: CurrentWeather = defaultCurrentWeather(),
    val hourlyWeather: HourlyWeather = defaultHourlyWeather(),
    val dailyWeather: DailyWeather = defaultDailyWeather(),
    val isLoadingCurrentWeather: Boolean = true,
    val isLoadingHourlyWeather: Boolean = true,
    val isLoadingDailyWeather: Boolean = true,
    val isError: Boolean = false
)
