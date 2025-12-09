package com.gzaber.weatherapp.data.repository.weather.model

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherUnitsExtTest {

    @Test
    fun stringToTemperatureUnit_returnsCorrectUnit() {
        assertEquals(TemperatureUnit.CELSIUS, "°C".toTemperatureUnit())
        assertEquals(TemperatureUnit.FAHRENHEIT, "°F".toTemperatureUnit())
        assertEquals(TemperatureUnit.UNKNOWN, "invalid".toTemperatureUnit())
    }

    @Test
    fun temperatureUnitToQueryValue_returnsCorrectString() {
        assertEquals("celsius", TemperatureUnit.CELSIUS.toQueryValue())
        assertEquals("fahrenheit", TemperatureUnit.FAHRENHEIT.toQueryValue())
        assertEquals("", TemperatureUnit.UNKNOWN.toQueryValue())
    }

    @Test
    fun stringToWindSpeedUnit_returnsCorrectUnit() {
        assertEquals(WindSpeedUnit.KILOMETERS_PER_HOUR, "km/h".toWindSpeedUnit())
        assertEquals(WindSpeedUnit.METERS_PER_SECOND, "m/s".toWindSpeedUnit())
        assertEquals(WindSpeedUnit.MILES_PER_HOUR, "mph".toWindSpeedUnit())
        assertEquals(WindSpeedUnit.KNOTS, "knots".toWindSpeedUnit())
        assertEquals(WindSpeedUnit.UNKNOWN, "invalid".toWindSpeedUnit())
    }

    @Test
    fun windSpeedUnitToQueryValue_returnsCorrectString() {
        assertEquals("kmh", WindSpeedUnit.KILOMETERS_PER_HOUR.toQueryValue())
        assertEquals("ms", WindSpeedUnit.METERS_PER_SECOND.toQueryValue())
        assertEquals("mph", WindSpeedUnit.MILES_PER_HOUR.toQueryValue())
        assertEquals("kn", WindSpeedUnit.KNOTS.toQueryValue())
        assertEquals("", WindSpeedUnit.UNKNOWN.toQueryValue())
    }

    @Test
    fun stringToPrecipitationUnit_returnsCorrectUnit() {
        assertEquals(PrecipitationUnit.MILLIMETER, "mm".toPrecipitationUnit())
        assertEquals(PrecipitationUnit.INCH, "inch".toPrecipitationUnit())
        assertEquals(PrecipitationUnit.UNKNOWN, "invalid".toPrecipitationUnit())
    }

    @Test
    fun precipitationUnitToQueryValue_returnsCorrectString() {
        assertEquals("mm", PrecipitationUnit.MILLIMETER.toQueryValue())
        assertEquals("inch", PrecipitationUnit.INCH.toQueryValue())
        assertEquals("", PrecipitationUnit.UNKNOWN.toQueryValue())
    }

    @Test
    fun stringToHumidityUnit_returnsCorrectUnit() {
        assertEquals(HumidityUnit.PERCENT, "%".toHumidityUnit())
        assertEquals(HumidityUnit.UNKNOWN, "invalid".toHumidityUnit())
    }
}
