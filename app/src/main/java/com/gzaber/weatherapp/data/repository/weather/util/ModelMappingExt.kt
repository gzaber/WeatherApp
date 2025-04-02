package com.gzaber.weatherapp.data.repository.weather.util

import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeatherParameter
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeatherParameters
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeatherParameters
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import java.time.LocalDate
import java.time.LocalDateTime

fun NetworkCurrentWeather.toExternal() = CurrentWeather(
    condition = values.weatherCode.toWeatherCondition(),
    temperature = CurrentWeatherParameter(
        unit = units.temperature.toTemperatureUnit(),
        value = values.temperature
    ),
    humidity = CurrentWeatherParameter(
        unit = units.humidity.toHumidityUnit(),
        value = values.humidity
    ),
    windSpeed = CurrentWeatherParameter(
        unit = units.windSpeed.toWindSpeedUnit(),
        value = values.windSpeed
    ),
    precipitation = CurrentWeatherParameter(
        unit = units.precipitation.toPrecipitationUnit(),
        value = values.precipitation
    )
)

fun NetworkHourlyWeather.toExternal() = HourlyWeather(
    temperatureUnit = units.temperature.toTemperatureUnit(),
    hourly = List(values.time.size) { i ->
        HourlyWeatherParameters(
            time = LocalDateTime.parse(values.time[i]),
            condition = values.weatherCodes[i].toWeatherCondition(),
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
            condition = values.weatherCodes[i].toWeatherCondition(),
            minTemperature = values.minTemperatures[i],
            maxTemperature = values.maxTemperature[i]
        )
    }
)