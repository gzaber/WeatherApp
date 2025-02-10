package com.gzaber.weatherapp.data.repository.weather.model

data class DailyWeather(
    val minTemperatureUnit: String,
    val maxTemperatureUnit: String,
    val daily: List<DailyWeatherParameters>
)
