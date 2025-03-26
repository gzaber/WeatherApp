package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.util.emptyCurrentWeather
import com.gzaber.weatherapp.ui.util.emptyDailyWeather
import com.gzaber.weatherapp.ui.util.emptyHourlyWeather
import com.gzaber.weatherapp.ui.util.emptyLocationPreferences
import com.gzaber.weatherapp.ui.util.emptyWeatherUnitsPreferences

data class WeatherUiState(
    val locationPreferences: LocationPreferences = emptyLocationPreferences(),
    val weatherUnitsPreferences: WeatherUnitsPreferences = emptyWeatherUnitsPreferences(),
    val currentWeather: CurrentWeather = emptyCurrentWeather(),
    val hourlyWeather: HourlyWeather = emptyHourlyWeather(),
    val dailyWeather: DailyWeather = emptyDailyWeather(),
    val weatherForecastType: WeatherForecastType = WeatherForecastType.HOURLY,
    val isLoadingCurrentWeather: Boolean = false,
    val isLoadingHourlyWeather: Boolean = false,
    val isLoadingDailyWeather: Boolean = false,
    val isError: Boolean = false
)

enum class WeatherForecastType {
    DAILY, HOURLY
}
