package com.gzaber.weatherapp.data.repository.weather.model

enum class TemperatureUnit {
    CELSIUS, FAHRENHEIT, UNKNOWN
}

enum class WindSpeedUnit {
    KILOMETERS_PER_HOUR, METERS_PER_SECOND, MILES_PER_HOUR, KNOTS, UNKNOWN
}

enum class PrecipitationUnit {
    MILLIMETER, INCH, UNKNOWN
}

enum class HumidityUnit {
    PERCENT, UNKNOWN
}

data class WeatherUnits(
    val temperatureUnit: TemperatureUnit = TemperatureUnit.CELSIUS,
    val windSpeedUnit: WindSpeedUnit = WindSpeedUnit.KILOMETERS_PER_HOUR,
    val precipitationUnit: PrecipitationUnit = PrecipitationUnit.MILLIMETER
)