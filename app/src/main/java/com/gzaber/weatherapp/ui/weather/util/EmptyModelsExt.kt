package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.settings.model.LocationSettings
import com.gzaber.weatherapp.data.repository.settings.model.WeatherUnitsSettings
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeatherParameter
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather

fun emptyLocationSettings() = LocationSettings(
    latitude = 0.0,
    longitude = 0.0,
    name = "",
    description = ""
)

fun emptyWeatherUnitsSettings() = WeatherUnitsSettings(
    temperatureUnit = "",
    windSpeedUnit = "",
    precipitationUnit = ""
)

fun emptyCurrentWeather() = CurrentWeather(
    weatherCode = 0,
    temperature = CurrentWeatherParameter(0.0, ""),
    humidity = CurrentWeatherParameter(0, ""),
    rain = CurrentWeatherParameter(0.0, ""),
    windSpeed = CurrentWeatherParameter(0.0, ""),
)

fun emptyHourlyWeather() = HourlyWeather(
    temperatureUnit = "",
    hourly = listOf()
)

fun emptyDailyWeather() = DailyWeather(
    minTemperatureUnit = "",
    maxTemperatureUnit = "",
    daily = listOf()
)