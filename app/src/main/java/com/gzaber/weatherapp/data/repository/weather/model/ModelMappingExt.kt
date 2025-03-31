package com.gzaber.weatherapp.data.repository.weather.model

import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import java.time.LocalDate
import java.time.LocalDateTime

fun NetworkCurrentWeather.toExternal() = CurrentWeather(
    weatherCode = values.weatherCode,
    temperature = CurrentWeatherParameter(
        unit = units.temperature.toTemperatureUnit(),
        value = values.temperature
    ),
    humidity = CurrentWeatherParameter(
        unit = units.humidity.toHumidityUnit(),
        value = values.humidity
    ),
    rain = CurrentWeatherParameter(
        unit = units.rain.toPrecipitationUnit(),
        value = values.rain
    ),
    windSpeed = CurrentWeatherParameter(
        unit = units.windSpeed.toWindSpeedUnit(),
        value = values.windSpeed
    )
)

fun NetworkHourlyWeather.toExternal() = HourlyWeather(
    temperatureUnit = units.temperature.toTemperatureUnit(),
    hourly = List(values.time.size) { i ->
        HourlyWeatherParameters(
            time = LocalDateTime.parse(values.time[i]),
            code = values.weatherCodes[i],
            temperature = values.temperatures[i]
        )
    }
)

fun NetworkDailyWeather.toExternal() = DailyWeather(
    temperatureUnit = if (units.minTemperature == units.maxTemperature)
        units.minTemperature.toTemperatureUnit() else TemperatureUnit.UNKNOWN,
    daily = List(values.date.size) { i ->
        DailyWeatherParameters(
            date = LocalDate.parse(values.date[i]),
            code = values.weatherCodes[i],
            minTemperature = values.minTemperatures[i],
            maxTemperature = values.maxTemperature[i]
        )
    }
)