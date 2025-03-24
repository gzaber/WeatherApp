package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.settings.model.LocationSettings
import com.gzaber.weatherapp.data.repository.settings.model.WeatherUnitsSettings
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.weather.util.emptyCurrentWeather
import com.gzaber.weatherapp.ui.weather.util.emptyDailyWeather
import com.gzaber.weatherapp.ui.weather.util.emptyHourlyWeather
import com.gzaber.weatherapp.ui.weather.util.emptyLocationSettings
import com.gzaber.weatherapp.ui.weather.util.emptyWeatherUnitsSettings

data class WeatherUiState(
    val locationSettings: LocationSettings = emptyLocationSettings(),
    val weatherUnitsSettings: WeatherUnitsSettings = emptyWeatherUnitsSettings(),
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
