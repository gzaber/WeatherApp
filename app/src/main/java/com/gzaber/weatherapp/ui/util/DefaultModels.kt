package com.gzaber.weatherapp.ui.util

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences

fun defaultLocationPreferences() = LocationPreferences(
    latitude = 52.22977,
    longitude = 21.01178,
    name = "Warsaw",
    country = "Poland"
)

fun defaultWeatherUnitsPreferences() = WeatherUnitsPreferences(
    temperatureUnit = "",
    windSpeedUnit = "",
    precipitationUnit = ""
)