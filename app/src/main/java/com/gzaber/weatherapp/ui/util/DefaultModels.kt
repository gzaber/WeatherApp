package com.gzaber.weatherapp.ui.util

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences

fun defaultLocationPreferences() = LocationPreferences(
    latitude = 0.0,
    longitude = 0.0,
    name = "",
    country = ""
)

fun defaultWeatherUnitsPreferences() = WeatherUnitsPreferences(
    temperatureUnit = "",
    windSpeedUnit = "",
    precipitationUnit = ""
)