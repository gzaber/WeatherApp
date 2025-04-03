package com.gzaber.weatherapp.ui.util

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeatherParameter
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HumidityUnit
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import java.time.LocalDateTime

fun emptyLocationPreferences() = LocationPreferences(
    latitude = 0.0,
    longitude = 0.0,
    name = "",
    country = ""
)

fun emptyWeatherUnitsPreferences() = WeatherUnitsPreferences(
    temperatureUnit = "",
    windSpeedUnit = "",
    precipitationUnit = ""
)

fun emptyCurrentWeather() = CurrentWeather(
    date = LocalDateTime.MIN,
    condition = WeatherCondition.UNKNOWN,
    temperature = CurrentWeatherParameter(unit = TemperatureUnit.UNKNOWN, value = 0.0),
    humidity = CurrentWeatherParameter(unit = HumidityUnit.UNKNOWN, 0),
    windSpeed = CurrentWeatherParameter(unit = WindSpeedUnit.UNKNOWN, 0.0),
    precipitation = CurrentWeatherParameter(unit = PrecipitationUnit.UNKNOWN, 0.0)
)

fun emptyHourlyWeather() = HourlyWeather(
    temperatureUnit = TemperatureUnit.UNKNOWN,
    hourly = listOf()
)

fun emptyDailyWeather() = DailyWeather(
    temperatureUnit = TemperatureUnit.UNKNOWN,
    daily = listOf()
)