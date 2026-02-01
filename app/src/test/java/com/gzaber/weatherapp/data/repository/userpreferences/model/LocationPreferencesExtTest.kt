package com.gzaber.weatherapp.data.repository.userpreferences.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LocationPreferencesExtTest {

    @Test
    fun isNotEmpty_returnsTrue_whenNameAndCountryAreNotBlank() {
        val locationPreferences = LocationPreferences(
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )
        assertTrue(locationPreferences.isNotEmpty())
    }

    @Test
    fun isNotEmpty_returnsFalse_whenNameIsBlank() {
        val locationPreferences = LocationPreferences(
            latitude = 52.23,
            longitude = 21.01,
            name = "",
            country = "Poland"
        )
        assertFalse(locationPreferences.isNotEmpty())
    }

    @Test
    fun isNotEmpty_returnsFalse_whenCountryIsBlank() {
        val locationPreferences = LocationPreferences(
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = ""
        )
        assertFalse(locationPreferences.isNotEmpty())
    }

    @Test
    fun isNotEmpty_returnsFalse_whenNameAndCountryAreBlank() {
        val locationPreferences = LocationPreferences(
            latitude = 52.23,
            longitude = 21.01,
            name = "",
            country = ""
        )
        assertFalse(locationPreferences.isNotEmpty())
    }
}
