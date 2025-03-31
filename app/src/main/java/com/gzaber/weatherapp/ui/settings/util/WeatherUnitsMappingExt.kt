package com.gzaber.weatherapp.ui.settings.util

import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit

fun TemperatureUnit.toSettingsDescription() = when (this) {
    TemperatureUnit.CELSIUS -> "Celsius °C"
    TemperatureUnit.FAHRENHEIT -> "Fahrenheit °F"
    else -> ""
}

fun String.toSettingsTemperatureUnit() = when (this) {
    "Celsius °C" -> TemperatureUnit.CELSIUS
    "Fahrenheit °F" -> TemperatureUnit.FAHRENHEIT
    else -> TemperatureUnit.UNKNOWN
}

fun WindSpeedUnit.toSettingsDescription() = when (this) {
    WindSpeedUnit.KILOMETERS_PER_HOUR -> "km/h"
    WindSpeedUnit.METERS_PER_SECOND -> "m/s"
    WindSpeedUnit.MILES_PER_HOUR -> "mph"
    WindSpeedUnit.KNOTS -> "Knots"
    else -> ""
}

fun String.toSettingsWindSpeedUnit() = when (this) {
    "km/h" -> WindSpeedUnit.KILOMETERS_PER_HOUR
    "m/s" -> WindSpeedUnit.METERS_PER_SECOND
    "mph" -> WindSpeedUnit.MILES_PER_HOUR
    "Knots" -> WindSpeedUnit.KNOTS
    else -> WindSpeedUnit.UNKNOWN
}

fun PrecipitationUnit.toSettingsDescription() = when (this) {
    PrecipitationUnit.MILLIMETER -> "Millimeter"
    PrecipitationUnit.INCH -> "Inch"
    else -> ""
}

fun String.toSettingsPrecipitationUnit() =
    when (this) {
        "Millimeter" -> PrecipitationUnit.MILLIMETER
        "Inch" -> PrecipitationUnit.INCH
        else -> PrecipitationUnit.UNKNOWN
    }
