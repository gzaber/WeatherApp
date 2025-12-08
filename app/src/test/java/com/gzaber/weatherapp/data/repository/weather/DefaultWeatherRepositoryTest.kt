package com.gzaber.weatherapp.data.repository.weather

import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.data.source.network.weather.WeatherApi
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeatherValues
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeatherValues
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeatherUnits
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeatherValues
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultWeatherRepositoryTest {

    private val networkDataSource: WeatherApi = mockk()
    private val repository = DefaultWeatherRepository(networkDataSource)

    @Test
    fun getCurrentWeather_returnsConvertedCurrentWeather() = runTest {
        val weatherUnits = WeatherUnits(
            TemperatureUnit.CELSIUS,
            WindSpeedUnit.KILOMETERS_PER_HOUR,
            PrecipitationUnit.MILLIMETER
        )
        val networkCurrentWeather = createNetworkCurrentWeather()

        coEvery {
            networkDataSource.getCurrentWeather(
                any(), any(), any(), any(), any()
            )
        } returns networkCurrentWeather

        val result = repository.getCurrentWeather(52.23, 21.01, weatherUnits)

        assertEquals(15.0, result.temperature.value, 0.0)
    }

    @Test
    fun getHourlyWeather_returnsConvertedHourlyWeather() = runTest {
        val temperatureUnit = TemperatureUnit.CELSIUS
        val networkHourlyWeather = createNetworkHourlyWeather()

        coEvery {
            networkDataSource.getHourlyWeather(any(), any(), any())
        } returns networkHourlyWeather

        val result = repository.getHourlyWeather(52.23, 21.01, temperatureUnit)

        assertEquals(1, result.hourly.size)
        assertEquals(15.0, result.hourly[0].temperature, 0.0)
    }

    @Test
    fun getDailyWeather_returnsConvertedDailyWeather() = runTest {
        val temperatureUnit = TemperatureUnit.CELSIUS
        val networkDailyWeather = createNetworkDailyWeather()

        coEvery {
            networkDataSource.getDailyWeather(any(), any(), any())
        } returns networkDailyWeather

        val result = repository.getDailyWeather(52.23, 21.01, temperatureUnit)

        assertEquals(1, result.daily.size)
        assertEquals(18.0, result.daily[0].maxTemperature, 0.0)
    }

    private fun createNetworkCurrentWeather() = NetworkCurrentWeather(
        values = NetworkCurrentWeatherValues(
            isDay = 1,
            weatherCode = 1,
            temperature = 15.0,
            humidity = 0,
            windSpeed = 0.0,
            precipitation = 0.0
        ),
        units = NetworkCurrentWeatherUnits(
            temperature = "°C",
            humidity = "%",
            windSpeed = "km/h",
            precipitation = "mm"
        )
    )

    private fun createNetworkHourlyWeather() = NetworkHourlyWeather(
        values = NetworkHourlyWeatherValues(
            time = listOf("2023-10-27T10:00"),
            isDay = listOf(1),
            weatherCodes = listOf(1),
            temperatures = listOf(15.0)
        ),
        units = NetworkHourlyWeatherUnits(temperature = "°C")
    )

    private fun createNetworkDailyWeather() = NetworkDailyWeather(
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
}
