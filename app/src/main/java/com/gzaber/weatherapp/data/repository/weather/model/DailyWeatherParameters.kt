package com.gzaber.weatherapp.data.repository.weather.model

import java.time.LocalDate

data class DailyWeatherParameters(
    val date: LocalDate,
    val code: Int,
    val minTemperature: Double,
    val maxTemperature: Double
)
