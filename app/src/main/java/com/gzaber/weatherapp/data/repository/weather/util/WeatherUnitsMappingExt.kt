package com.gzaber.weatherapp.data.repository.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.HumidityUnit
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit

fun String.toTemperatureUnit() = when (this) {
    "°C" -> TemperatureUnit.CELSIUS
    "°F" -> TemperatureUnit.FAHRENHEIT
    else -> TemperatureUnit.UNKNOWN
}

fun TemperatureUnit.toQueryValue() = when (this) {
    TemperatureUnit.CELSIUS -> "celsius"
    TemperatureUnit.FAHRENHEIT -> "fahrenheit"
    TemperatureUnit.UNKNOWN -> ""
}

fun String.toWindSpeedUnit() = when (this) {
    "km/h" -> WindSpeedUnit.KILOMETERS_PER_HOUR
    "m/s" -> WindSpeedUnit.METERS_PER_SECOND
    "mph" -> WindSpeedUnit.MILES_PER_HOUR
    "knots" -> WindSpeedUnit.KNOTS
    else -> WindSpeedUnit.UNKNOWN
}

fun WindSpeedUnit.toQueryValue() = when (this) {
    WindSpeedUnit.KILOMETERS_PER_HOUR -> "kmh"
    WindSpeedUnit.METERS_PER_SECOND -> "ms"
    WindSpeedUnit.MILES_PER_HOUR -> "mph"
    WindSpeedUnit.KNOTS -> "kn"
    WindSpeedUnit.UNKNOWN -> ""
}

fun String.toPrecipitationUnit() = when (this) {
    "mm" -> PrecipitationUnit.MILLIMETER
    "inch" -> PrecipitationUnit.INCH
    else -> PrecipitationUnit.UNKNOWN
}

fun PrecipitationUnit.toQueryValue() = when (this) {
    PrecipitationUnit.MILLIMETER -> "mm"
    PrecipitationUnit.INCH -> "inch"
    PrecipitationUnit.UNKNOWN -> ""
}

fun String.toHumidityUnit() = when (this) {
    "%" -> HumidityUnit.PERCENT
    else -> HumidityUnit.UNKNOWN
}