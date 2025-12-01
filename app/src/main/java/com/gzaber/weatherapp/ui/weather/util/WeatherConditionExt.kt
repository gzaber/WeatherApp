package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition

fun WeatherCondition.toDescription(): String = when (this) {
    WeatherCondition.CLEAR_SKY -> "Clear sky"
    WeatherCondition.MAINLY_CLEAR -> "Mainly clear"
    WeatherCondition.PARTLY_CLOUDY -> "Partly cloudy"
    WeatherCondition.OVERCAST -> "Overcast"
    WeatherCondition.FOG -> "Fog"
    WeatherCondition.FOG_DEPOSITING_RIME -> "Depositing rime fog"
    WeatherCondition.DRIZZLE_LIGHT -> "Light drizzle"
    WeatherCondition.DRIZZLE_MODERATE -> "Moderate drizzle"
    WeatherCondition.DRIZZLE_DENSE -> "Dense drizzle"
    WeatherCondition.FREEZING_DRIZZLE_LIGHT -> "Light freezing drizzle"
    WeatherCondition.FREEZING_DRIZZLE_DENSE -> "Dense freezing drizzle"
    WeatherCondition.RAIN_SLIGHT -> "Slight rain"
    WeatherCondition.RAIN_MODERATE -> "Moderate rain"
    WeatherCondition.RAIN_HEAVY -> "Heavy rain"
    WeatherCondition.FREEZING_RAIN_LIGHT -> "Light freezing rain"
    WeatherCondition.FREEZING_RAIN_HEAVY -> "Heavy freezing rain"
    WeatherCondition.SNOW_FALL_SLIGHT -> "Slight snow fall"
    WeatherCondition.SNOW_FALL_MODERATE -> "Moderate snow fall"
    WeatherCondition.SNOW_FALL_HEAVY -> "Heavy snow fall"
    WeatherCondition.SNOW_GRAINS -> "Snow grains"
    WeatherCondition.RAIN_SHOWERS_SLIGHT -> "Slight rain showers"
    WeatherCondition.RAIN_SHOWERS_MODERATE -> "Moderate rain showers"
    WeatherCondition.RAIN_SHOWERS_VIOLENT -> "Violent rain showers"
    WeatherCondition.SNOW_SHOWERS_SLIGHT -> "Slight snow showers"
    WeatherCondition.SNOW_SHOWERS_HEAVY -> "Heavy snow showers"
    WeatherCondition.THUNDERSTORM -> "Thunderstorm"
    WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT -> "Thunderstorm with slight hail"
    WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY -> "Thunderstorm with heavy hail"
    WeatherCondition.UNKNOWN -> ""
}

fun WeatherCondition.toImageUrl(isDaytime: Boolean = true, darkMode: Boolean = false): String {
    val baseUrl = "https://maps.gstatic.com/weather/v1"
    val timeSegment = if (isDaytime) "" else "_night"
    val modeSuffix = if (darkMode) "_dark" else ""

    val iconPath = when (this) {
        WeatherCondition.CLEAR_SKY -> "clear"
        WeatherCondition.MAINLY_CLEAR -> "mostly_clear"
        WeatherCondition.PARTLY_CLOUDY -> "partly_cloudy"
        WeatherCondition.OVERCAST -> "cloudy"
        WeatherCondition.FOG, WeatherCondition.FOG_DEPOSITING_RIME -> "fog"
        WeatherCondition.DRIZZLE_LIGHT, WeatherCondition.DRIZZLE_MODERATE, WeatherCondition.DRIZZLE_DENSE -> "light_rain"
        WeatherCondition.FREEZING_DRIZZLE_LIGHT, WeatherCondition.FREEZING_DRIZZLE_DENSE, WeatherCondition.FREEZING_RAIN_LIGHT,
        WeatherCondition.RAIN_SLIGHT, WeatherCondition.RAIN_MODERATE -> "rain"

        WeatherCondition.RAIN_HEAVY, WeatherCondition.FREEZING_RAIN_HEAVY -> "heavy_rain"
        WeatherCondition.SNOW_FALL_SLIGHT, WeatherCondition.SNOW_FALL_MODERATE,
        WeatherCondition.SNOW_FALL_HEAVY, WeatherCondition.SNOW_GRAINS -> "snow"

        WeatherCondition.SNOW_SHOWERS_SLIGHT, WeatherCondition.SNOW_SHOWERS_HEAVY -> "snow_showers"
        WeatherCondition.RAIN_SHOWERS_SLIGHT, WeatherCondition.RAIN_SHOWERS_MODERATE, WeatherCondition.RAIN_SHOWERS_VIOLENT -> "rain_showers"
        WeatherCondition.THUNDERSTORM, WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT, WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY -> "thunderstorm"
        WeatherCondition.UNKNOWN -> "unknown"
    }

    return "$baseUrl/$iconPath$timeSegment$modeSuffix.png"
}