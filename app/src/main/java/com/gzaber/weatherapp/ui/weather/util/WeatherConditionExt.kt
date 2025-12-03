package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition
import com.gzaber.weatherapp.R

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

fun WeatherCondition.toIconRes(isDay: Boolean = true): Int {
    return when (this) {
        WeatherCondition.CLEAR_SKY,
        WeatherCondition.MAINLY_CLEAR ->
            if (isDay) R.drawable.ic_weather_clear_day else R.drawable.ic_weather_clear_night

        WeatherCondition.PARTLY_CLOUDY ->
            if (isDay) R.drawable.ic_weather_partly_cloudy_day else R.drawable.ic_weather_partly_cloudy_night

        WeatherCondition.OVERCAST ->
            R.drawable.ic_weather_cloudy

        WeatherCondition.FOG,
        WeatherCondition.FOG_DEPOSITING_RIME ->
            R.drawable.ic_weather_fog

        WeatherCondition.DRIZZLE_LIGHT,
        WeatherCondition.DRIZZLE_MODERATE,
        WeatherCondition.DRIZZLE_DENSE,
        WeatherCondition.FREEZING_DRIZZLE_LIGHT,
        WeatherCondition.RAIN_SLIGHT,
        WeatherCondition.FREEZING_RAIN_LIGHT,
        WeatherCondition.RAIN_SHOWERS_SLIGHT ->
            R.drawable.ic_weather_rain_light

        WeatherCondition.FREEZING_DRIZZLE_DENSE,
        WeatherCondition.RAIN_MODERATE,
        WeatherCondition.RAIN_SHOWERS_MODERATE ->
            R.drawable.ic_weather_rain_moderate

        WeatherCondition.RAIN_HEAVY,
        WeatherCondition.FREEZING_RAIN_HEAVY ->
            R.drawable.ic_weather_rain_heavy

        WeatherCondition.RAIN_SHOWERS_VIOLENT ->
            R.drawable.ic_weather_rain_violent

        WeatherCondition.SNOW_FALL_SLIGHT,
        WeatherCondition.SNOW_SHOWERS_SLIGHT,
        WeatherCondition.SNOW_GRAINS ->
            R.drawable.ic_weather_snow_light

        WeatherCondition.SNOW_FALL_MODERATE ->
            R.drawable.ic_weather_snow_moderate

        WeatherCondition.SNOW_FALL_HEAVY,
        WeatherCondition.SNOW_SHOWERS_HEAVY ->
            R.drawable.ic_weather_snow_heavy

        WeatherCondition.THUNDERSTORM ->
            R.drawable.ic_weather_thunderstorm

        WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT,
        WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY ->
            R.drawable.ic_weather_thunderstorm_with_hail

        WeatherCondition.UNKNOWN ->
            R.drawable.ic_weather_unknown
    }
}