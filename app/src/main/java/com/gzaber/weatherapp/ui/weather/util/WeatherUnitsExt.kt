package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.HumidityUnit
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit

fun TemperatureUnit.toSymbol() = when (this) {
    TemperatureUnit.CELSIUS -> "°C"
    TemperatureUnit.FAHRENHEIT -> "°F"
    else -> ""
}

fun WindSpeedUnit.toSymbol() = when (this) {
    WindSpeedUnit.KILOMETERS_PER_HOUR -> "km/h"
    WindSpeedUnit.METERS_PER_SECOND -> "m/s"
    WindSpeedUnit.MILES_PER_HOUR -> "mph"
    WindSpeedUnit.KNOTS -> "kn"
    else -> ""
}

fun PrecipitationUnit.toSymbol() = when (this) {
    PrecipitationUnit.MILLIMETER -> "mm"
    PrecipitationUnit.INCH -> "in"
    else -> ""
}

fun HumidityUnit.toSymbol() = when (this) {
    HumidityUnit.PERCENT -> "%"
    else -> ""
}