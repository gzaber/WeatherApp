package com.gzaber.weatherapp.data.repository.weather.model

import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeatherValues
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeatherValues
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeatherValues
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ModelMappingExtTest {

    @Test
    fun networkCurrentWeather_toExternal_returnsCorrectCurrentWeather() {
        val networkCurrentWeather = NetworkCurrentWeather(
            values = NetworkCurrentWeatherValues(
                isDay = 1,
                weatherCode = 1,
                temperature = 15.0,
                humidity = 80,
                windSpeed = 10.0,
                precipitation = 0.0
            ),
            units = NetworkCurrentWeatherUnits(
                temperature = "°C",
                humidity = "%",
                windSpeed = "km/h",
                precipitation = "mm"
            )
        )

        val result = networkCurrentWeather.toExternal()

        assertEquals(true, result.isDay)
        assertEquals(WeatherCondition.MAINLY_CLEAR, result.condition)
        assertEquals(TemperatureUnit.CELSIUS, result.temperature.unit)
        assertEquals(15.0, result.temperature.value, 0.0)
        assertEquals(HumidityUnit.PERCENT, result.humidity.unit)
        assertEquals(80, result.humidity.value)
        assertEquals(WindSpeedUnit.KILOMETERS_PER_HOUR, result.windSpeed.unit)
        assertEquals(10.0, result.windSpeed.value, 0.0)
        assertEquals(PrecipitationUnit.MILLIMETER, result.precipitation.unit)
        assertEquals(0.0, result.precipitation.value, 0.0)
    }

    @Test
    fun networkHourlyWeather_toExternal_returnsCorrectHourlyWeather() {
        val networkHourlyWeather = NetworkHourlyWeather(
            values = NetworkHourlyWeatherValues(
                time = listOf("2023-10-27T10:00"),
                isDay = listOf(1),
                weatherCodes = listOf(1),
                temperatures = listOf(15.0)
            ),
            units = NetworkHourlyWeatherUnits(
                temperature = "°C"
            )
        )

        val result = networkHourlyWeather.toExternal()

        assertEquals(TemperatureUnit.CELSIUS, result.temperatureUnit)
        assertEquals(1, result.hourly.size)
        assertEquals(LocalDateTime.parse("2023-10-27T10:00"), result.hourly[0].time)
        assertEquals(true, result.hourly[0].isDay)
        assertEquals(WeatherCondition.MAINLY_CLEAR, result.hourly[0].condition)
        assertEquals(15.0, result.hourly[0].temperature, 0.0)
    }

    @Test
    fun networkDailyWeather_toExternal_returnsCorrectDailyWeather() {
        val networkDailyWeather = NetworkDailyWeather(
            values = NetworkDailyWeatherValues(
                date = listOf("2023-10-27"),
                weatherCodes = listOf(1),
                minTemperatures = listOf(10.0),
                maxTemperatures = listOf(18.0)
            ),
            units = NetworkDailyWeatherUnits(
                minTemperature = "°C",
                maxTemperature = "°C"
            )
        )

        val result = networkDailyWeather.toExternal()

        assertEquals(TemperatureUnit.CELSIUS, result.temperatureUnit)
        assertEquals(1, result.daily.size)
        assertEquals(LocalDate.parse("2023-10-27"), result.daily[0].date)
        assertEquals(WeatherCondition.MAINLY_CLEAR, result.daily[0].condition)
        assertEquals(10.0, result.daily[0].minTemperature, 0.0)
        assertEquals(18.0, result.daily[0].maxTemperature, 0.0)
    }

    @Test
    fun networkDailyWeather_toExternal_returnsUnknownTemperatureUnit_whenUnitsMismatch() {
        val networkDailyWeather = NetworkDailyWeather(
            values = NetworkDailyWeatherValues(
                date = listOf("2023-10-27"),
                weatherCodes = listOf(1),
                minTemperatures = listOf(10.0),
                maxTemperatures = listOf(18.0)
            ),
            units = NetworkDailyWeatherUnits(
                minTemperature = "°C",
                maxTemperature = "°F"
            )
        )

        val result = networkDailyWeather.toExternal()

        assertEquals(TemperatureUnit.UNKNOWN, result.temperatureUnit)
    }
}
