package com.gzaber.weatherapp.data.repository.weather.model

import java.time.LocalDateTime

data class HourlyWeatherParameters(
    val time: LocalDateTime,
    val code: Int,
    val temperature: Double
)
