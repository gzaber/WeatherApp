package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.R
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

fun WeatherCondition.toDrawable() = when (this) {
    WeatherCondition.CLEAR_SKY,
    WeatherCondition.MAINLY_CLEAR -> R.drawable.ic_weather_clear_sky

    WeatherCondition.PARTLY_CLOUDY -> R.drawable.ic_weather_partly_cloudy
    WeatherCondition.OVERCAST -> R.drawable.ic_weather_cloudy
    WeatherCondition.FOG,
    WeatherCondition.FOG_DEPOSITING_RIME -> R.drawable.ic_weather_foggy

    WeatherCondition.DRIZZLE_LIGHT,
    WeatherCondition.DRIZZLE_MODERATE,
    WeatherCondition.DRIZZLE_DENSE,
    WeatherCondition.FREEZING_DRIZZLE_LIGHT,
    WeatherCondition.FREEZING_DRIZZLE_DENSE,
    WeatherCondition.RAIN_SLIGHT,
    WeatherCondition.RAIN_MODERATE,
    WeatherCondition.RAIN_HEAVY,
    WeatherCondition.FREEZING_RAIN_LIGHT,
    WeatherCondition.FREEZING_RAIN_HEAVY,
    WeatherCondition.RAIN_SHOWERS_SLIGHT,
    WeatherCondition.RAIN_SHOWERS_MODERATE,
    WeatherCondition.RAIN_SHOWERS_VIOLENT -> R.drawable.ic_weather_rainy

    WeatherCondition.SNOW_FALL_SLIGHT,
    WeatherCondition.SNOW_FALL_MODERATE,
    WeatherCondition.SNOW_FALL_HEAVY,
    WeatherCondition.SNOW_GRAINS,
    WeatherCondition.SNOW_SHOWERS_SLIGHT,
    WeatherCondition.SNOW_SHOWERS_HEAVY -> R.drawable.ic_weather_snowy

    WeatherCondition.THUNDERSTORM,
    WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT,
    WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY -> R.drawable.ic_weather_thunderstorm

    else -> R.drawable.ic_weather_unknown
}