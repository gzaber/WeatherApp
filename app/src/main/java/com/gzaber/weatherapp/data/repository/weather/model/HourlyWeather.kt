package com.gzaber.weatherapp.data.repository.weather.model

data class HourlyWeather(
    val temperatureUnit: String,
    val hourly: List<HourlyWeatherParameters>
)
