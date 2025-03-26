package com.gzaber.weatherapp.data.repository.weather.model

enum class TemperatureUnit(val value: String) {
    CELSIUS("celsius"),
    FAHRENHEIT("fahrenheit")
}

enum class WindSpeedUnit(val value: String) {
    KILOMETERS_PER_HOUR("kmh"),
    METERS_PER_SECOND("ms"),
    MILES_PER_HOUR("mph"),
    KNOTS("kn")
}

enum class PrecipitationUnit(val value: String) {
    MILLIMETER("mm"),
    INCH("inch")
}

data class WeatherUnits(
    val temperatureUnit: TemperatureUnit = TemperatureUnit.CELSIUS,
    val windSpeedUnit: WindSpeedUnit = WindSpeedUnit.KILOMETERS_PER_HOUR,
    val precipitationUnit: PrecipitationUnit = PrecipitationUnit.MILLIMETER
)
