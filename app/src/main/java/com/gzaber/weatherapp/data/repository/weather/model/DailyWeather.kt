package com.gzaber.weatherapp.data.repository.weather.model

data class DailyWeather(
    val temperatureUnit: TemperatureUnit,
    val daily: List<DailyWeatherParameters>
)
