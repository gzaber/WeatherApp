package com.gzaber.weatherapp.data.repository.weather.model

import java.time.LocalDateTime

data class HourlyWeatherParameters(
    val time: LocalDateTime,
    val isDay: Boolean,
    val condition: WeatherCondition,
    val temperature: Double
)
