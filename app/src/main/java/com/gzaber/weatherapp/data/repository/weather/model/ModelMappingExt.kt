package com.gzaber.weatherapp.data.repository.weather.model

import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import java.time.LocalDate
import java.time.LocalDateTime

fun NetworkCurrentWeather.toExternal() = CurrentWeather(
    weatherCode = values.weatherCode,
    temperature = CurrentWeatherParameter(value = values.temperature, unit = units.temperature),
    humidity = CurrentWeatherParameter(value = values.humidity, unit = units.humidity),
    rain = CurrentWeatherParameter(value = values.rain, unit = units.rain),
    windSpeed = CurrentWeatherParameter(value = values.windSpeed, unit = units.windSpeed)
)

fun NetworkHourlyWeather.toExternal() = HourlyWeather(
    temperatureUnit = units.temperature,
    hourly = List(values.time.size) { i ->
        HourlyWeatherParameters(
            time = LocalDateTime.parse(values.time[i]),
            code = values.weatherCodes[i],
            temperature = values.temperatures[i]
        )
    }
)

fun NetworkDailyWeather.toExternal() = DailyWeather(
    minTemperatureUnit = units.minTemperature,
    maxTemperatureUnit = units.maxTemperature,
    daily = List(values.date.size) { i ->
        DailyWeatherParameters(
            date = LocalDate.parse(values.date[i]),
            code = values.weatherCodes[i],
            minTemperature = values.minTemperatures[i],
            maxTemperature = values.maxTemperature[i]
        )
    }
)