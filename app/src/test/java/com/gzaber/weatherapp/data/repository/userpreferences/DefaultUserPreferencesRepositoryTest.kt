package com.gzaber.weatherapp.data.repository.userpreferences

import androidx.datastore.core.DataStore
import com.gzaber.weatherapp.UserPreferences
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultUserPreferencesRepositoryTest {

    private val settingsStore: DataStore<UserPreferences> = mockk(relaxed = true)
    private val repository = DefaultUserPreferencesRepository(settingsStore)

    @Test
    fun updateLocation_updatesDataStore() = runTest {
        repository.updateLocation(52.23, 21.01, "Warsaw", "Poland")

        coVerify { settingsStore.updateData(any()) }
    }

    @Test
    fun updateTemperatureUnit_updatesDataStore() = runTest {
        repository.updateTemperatureUnit("celsius")

        coVerify { settingsStore.updateData(any()) }
    }

    @Test
    fun updateWindSpeedUnit_updatesDataStore() = runTest {
        repository.updateWindSpeedUnit("kmh")

        coVerify { settingsStore.updateData(any()) }
    }

    @Test
    fun updatePrecipitationUnit_updatesDataStore() = runTest {
        repository.updatePrecipitationUnit("mm")

        coVerify { settingsStore.updateData(any()) }
    }

    @Test
    fun observeLocation_returnsFlowOfLocationPreferences() = runTest {
        val userPreferences = UserPreferences.newBuilder()
            .setLatitude(52.23)
            .setLongitude(21.01)
            .setName("Warsaw")
            .setCountry("Poland")
            .build()
        every { settingsStore.data } returns flowOf(userPreferences)

        val result = repository.observeLocation().first()

        assertEquals("Warsaw", result.name)
        assertEquals("Poland", result.country)
    }

    @Test
    fun observeWeatherUnits_returnsFlowOfWeatherUnitsPreferences() = runTest {
        val userPreferences = UserPreferences.newBuilder()
            .setTemperatureUnit("celsius")
            .setWindSpeedUnit("kmh")
            .setPrecipitationUnit("mm")
            .build()
        every { settingsStore.data } returns flowOf(userPreferences)

        val result = repository.observeWeatherUnits().first()

        assertEquals("celsius", result.temperatureUnit)
        assertEquals("kmh", result.windSpeedUnit)
        assertEquals("mm", result.precipitationUnit)
    }
}
