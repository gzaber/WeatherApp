package com.gzaber.weatherapp.ui.util

import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeatherParameter
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather

fun emptyLocation() = Location(
    id = "",
    name = "",
    latitude = 0.0,
    longitude = 0.0,
    description = ""
)

fun emptyCurrentWeather() = CurrentWeather(
    weatherCode = 0,
    temperature = CurrentWeatherParameter(0.0, ""),
    humidity = CurrentWeatherParameter(0, ""),
    rain = CurrentWeatherParameter(0, ""),
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