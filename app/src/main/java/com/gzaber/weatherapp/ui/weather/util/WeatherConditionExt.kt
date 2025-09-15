package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition

fun WeatherCondition.toDescription() = when (this) {
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

fun WeatherCondition.toImageLink() = when (this) {
    WeatherCondition.CLEAR_SKY -> "https://maps.gstatic.com/weather/v1/sunny.png"
    WeatherCondition.MAINLY_CLEAR -> "https://maps.gstatic.com/weather/v1/mostly_sunny.png"
    WeatherCondition.PARTLY_CLOUDY -> "https://maps.gstatic.com/weather/v1/partly_cloudy.png"
    WeatherCondition.OVERCAST,
    WeatherCondition.FOG,
    WeatherCondition.FOG_DEPOSITING_RIME -> "https://maps.gstatic.com/weather/v1/cloudy.png"

    WeatherCondition.DRIZZLE_LIGHT,
    WeatherCondition.FREEZING_DRIZZLE_LIGHT,
    WeatherCondition.FREEZING_RAIN_LIGHT,
    WeatherCondition.RAIN_SLIGHT,
    WeatherCondition.RAIN_SHOWERS_SLIGHT -> "https://maps.gstatic.com/weather/v1/drizzle.png"

    WeatherCondition.DRIZZLE_MODERATE,
    WeatherCondition.FREEZING_DRIZZLE_DENSE,
    WeatherCondition.RAIN_MODERATE,
    WeatherCondition.RAIN_SHOWERS_MODERATE -> "https://maps.gstatic.com/weather/v1/scattered_showers.png"

    WeatherCondition.DRIZZLE_DENSE,
    WeatherCondition.FREEZING_RAIN_HEAVY,
    WeatherCondition.RAIN_HEAVY,
    WeatherCondition.RAIN_SHOWERS_VIOLENT -> "https://maps.gstatic.com/weather/v1/showers.png"

    WeatherCondition.SNOW_FALL_MODERATE -> "https://maps.gstatic.com/weather/v1/snow_showers.png"
    WeatherCondition.SNOW_FALL_SLIGHT,
    WeatherCondition.SNOW_GRAINS -> "https://maps.gstatic.com/weather/v1/flurries.png"

    WeatherCondition.SNOW_SHOWERS_SLIGHT -> "https://maps.gstatic.com/weather/v1/scattered_snow.png"
    WeatherCondition.SNOW_FALL_HEAVY,
    WeatherCondition.SNOW_SHOWERS_HEAVY -> "https://maps.gstatic.com/weather/v1/heavy_snow.png"

    WeatherCondition.THUNDERSTORM,
    WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT,
    WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY -> "https://maps.gstatic.com/weather/v1/strong_tstorms.png"

    else -> "https://maps.gstatic.com/weather/v1/windy_breezy.png"
}