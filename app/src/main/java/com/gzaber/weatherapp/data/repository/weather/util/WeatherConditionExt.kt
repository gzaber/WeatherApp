package com.gzaber.weatherapp.data.repository.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition

fun Int.toWeatherCondition() = when (this) {
    0 -> WeatherCondition.CLEAR_SKY
    1 -> WeatherCondition.MAINLY_CLEAR
    2 -> WeatherCondition.PARTLY_CLOUDY
    3 -> WeatherCondition.OVERCAST
    45 -> WeatherCondition.FOG
    48 -> WeatherCondition.FOG_DEPOSITING_RIME
    51 -> WeatherCondition.DRIZZLE_LIGHT
    53 -> WeatherCondition.DRIZZLE_MODERATE
    55 -> WeatherCondition.DRIZZLE_DENSE
    56 -> WeatherCondition.FREEZING_DRIZZLE_LIGHT
    57 -> WeatherCondition.FREEZING_DRIZZLE_DENSE
    61 -> WeatherCondition.RAIN_SLIGHT
    63 -> WeatherCondition.RAIN_MODERATE
    65 -> WeatherCondition.RAIN_HEAVY
    66 -> WeatherCondition.FREEZING_RAIN_LIGHT
    67 -> WeatherCondition.FREEZING_RAIN_HEAVY
    71 -> WeatherCondition.SNOW_FALL_SLIGHT
    73 -> WeatherCondition.SNOW_FALL_MODERATE
    75 -> WeatherCondition.SNOW_FALL_HEAVY
    77 -> WeatherCondition.SNOW_GRAINS
    80 -> WeatherCondition.RAIN_SHOWERS_SLIGHT
    81 -> WeatherCondition.RAIN_SHOWERS_MODERATE
    82 -> WeatherCondition.RAIN_SHOWERS_VIOLENT
    85 -> WeatherCondition.SNOW_SHOWERS_SLIGHT
    86 -> WeatherCondition.SNOW_SHOWERS_HEAVY
    95 -> WeatherCondition.THUNDERSTORM
    96 -> WeatherCondition.THUNDERSTORM_WITH_HAIL_SLIGHT
    99 -> WeatherCondition.THUNDERSTORM_WITH_HAIL_HEAVY
    else -> WeatherCondition.UNKNOWN
}