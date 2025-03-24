package com.gzaber.weatherapp.ui.weather.util

import com.gzaber.weatherapp.data.repository.settings.model.WeatherUnitsSettings
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit

fun WeatherUnitsSettings.toWeatherUnits() = WeatherUnits(
    temperatureUnit = if (temperatureUnit.isNotBlank())
        TemperatureUnit.valueOf(temperatureUnit) else TemperatureUnit.CELSIUS,
    windSpeedUnit = if (windSpeedUnit.isNotBlank())
        WindSpeedUnit.valueOf(windSpeedUnit) else WindSpeedUnit.KILOMETERS_PER_HOUR,
    precipitationUnit = if (precipitationUnit.isNotBlank())
        PrecipitationUnit.valueOf(precipitationUnit) else PrecipitationUnit.MILLIMETER
)