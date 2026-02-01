package com.gzaber.weatherapp.data.repository.userpreferences

import androidx.datastore.core.DataStore
import com.gzaber.weatherapp.UserPreferences
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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
        val transformSlot = slot<suspend (UserPreferences) -> UserPreferences>()
        coEvery { settingsStore.updateData(capture(transformSlot)) } returns UserPreferences.getDefaultInstance()

        repository.updateLocation(52.23, 21.01, "Warsaw", "Poland")

        val result = transformSlot.captured(UserPreferences.getDefaultInstance())
        assertEquals(52.23, result.latitude, 0.0)
        assertEquals(21.01, result.longitude, 0.0)
        assertEquals("Warsaw", result.name)
        assertEquals("Poland", result.country)
    }

    @Test
    fun updateTemperatureUnit_updatesDataStore() = runTest {
        val transformSlot = slot<suspend (UserPreferences) -> UserPreferences>()
        coEvery { settingsStore.updateData(capture(transformSlot)) } returns UserPreferences.getDefaultInstance()

        repository.updateTemperatureUnit("celsius")

        val result = transformSlot.captured(UserPreferences.getDefaultInstance())
        assertEquals("celsius", result.temperatureUnit)
    }

    @Test
    fun updateWindSpeedUnit_updatesDataStore() = runTest {
        val transformSlot = slot<suspend (UserPreferences) -> UserPreferences>()
        coEvery { settingsStore.updateData(capture(transformSlot)) } returns UserPreferences.getDefaultInstance()

        repository.updateWindSpeedUnit("kmh")

        val result = transformSlot.captured(UserPreferences.getDefaultInstance())
        assertEquals("kmh", result.windSpeedUnit)
    }

    @Test
    fun updatePrecipitationUnit_updatesDataStore() = runTest {
        val transformSlot = slot<suspend (UserPreferences) -> UserPreferences>()
        coEvery { settingsStore.updateData(capture(transformSlot)) } returns UserPreferences.getDefaultInstance()

        repository.updatePrecipitationUnit("mm")

        val result = transformSlot.captured(UserPreferences.getDefaultInstance())
        assertEquals("mm", result.precipitationUnit)
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
        assertEquals(52.23, result.latitude, 0.0)
        assertEquals(21.01, result.longitude, 0.0)
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
