package com.gzaber.weatherapp.data.repository.weather.model

data class HourlyWeather(
    val temperatureUnit: TemperatureUnit,
    val hourly: List<HourlyWeatherParameters>
)
