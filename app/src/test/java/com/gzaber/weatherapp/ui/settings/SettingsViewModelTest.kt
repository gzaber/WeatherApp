package com.gzaber.weatherapp.ui.settings

import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.ui.settings.util.toSettingsDescription
import com.gzaber.weatherapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var viewModel: SettingsViewModel

    val weatherUnitsPreferences = WeatherUnitsPreferences(
        temperatureUnit = TemperatureUnit.CELSIUS.name,
        windSpeedUnit = WindSpeedUnit.KILOMETERS_PER_HOUR.name,
        precipitationUnit = PrecipitationUnit.MILLIMETER.name
    )

    @Before
    fun setUp() {
        userPreferencesRepository = mockk(relaxed = true)

        every { userPreferencesRepository.observeWeatherUnits() } returns flowOf(
            weatherUnitsPreferences
        )
    }

    @Test
    fun init_emitsSuccessStateWithWeatherUnits() = runTest {
        viewModel = SettingsViewModel(userPreferencesRepository)

        val state = viewModel.uiState.value
        assertEquals(TemperatureUnit.CELSIUS.toSettingsDescription(), state.selectedTemperatureUnit)
        assertEquals(
            WindSpeedUnit.KILOMETERS_PER_HOUR.toSettingsDescription(),
            state.selectedWindSpeedUnit
        )
        assertEquals(
            PrecipitationUnit.MILLIMETER.toSettingsDescription(),
            state.selectedPrecipitationUnit
        )

        val expectedWeatherUnits = WeatherUnits(
            TemperatureUnit.CELSIUS,
            WindSpeedUnit.KILOMETERS_PER_HOUR,
            PrecipitationUnit.MILLIMETER
        )
        assertEquals(SettingsDataState.Success(expectedWeatherUnits), state.settingsDataState)
    }

    @Test
    fun init_repositoryThrows_emitsErrorState() = runTest {
        every { userPreferencesRepository.observeWeatherUnits() } returns flowOf(
            weatherUnitsPreferences
        ).onStart { throw Exception() }

        viewModel = SettingsViewModel(userPreferencesRepository)

        assertEquals(SettingsDataState.Error, viewModel.uiState.value.settingsDataState)
    }

    @Test
    fun onTemperatureUnitSelected_updatesRepository() = runTest {
        viewModel = SettingsViewModel(userPreferencesRepository)

        viewModel.onTemperatureUnitSelected(TemperatureUnit.FAHRENHEIT.toSettingsDescription())

        coVerify { userPreferencesRepository.updateTemperatureUnit(TemperatureUnit.FAHRENHEIT.name) }
    }

    @Test
    fun onTemperatureUnitSelected_repositoryThrows_emitsErrorState() = runTest {
        coEvery { userPreferencesRepository.updateTemperatureUnit(any()) } throws Exception()

        viewModel = SettingsViewModel(userPreferencesRepository)
        viewModel.onTemperatureUnitSelected(TemperatureUnit.FAHRENHEIT.toSettingsDescription())

        assertEquals(SettingsDataState.Error, viewModel.uiState.value.settingsDataState)
    }

    @Test
    fun onWindSpeedUnitSelected_updatesRepository() = runTest {
        viewModel = SettingsViewModel(userPreferencesRepository)

        viewModel.onWindSpeedUnitSelected(WindSpeedUnit.METERS_PER_SECOND.toSettingsDescription())

        coVerify { userPreferencesRepository.updateWindSpeedUnit(WindSpeedUnit.METERS_PER_SECOND.name) }
    }

    @Test
    fun onWindSpeedUnitSelected_repositoryThrows_emitsErrorState() = runTest {
        coEvery { userPreferencesRepository.updateWindSpeedUnit(any()) } throws Exception()

        viewModel = SettingsViewModel(userPreferencesRepository)
        viewModel.onWindSpeedUnitSelected(WindSpeedUnit.METERS_PER_SECOND.toSettingsDescription())

        assertEquals(SettingsDataState.Error, viewModel.uiState.value.settingsDataState)
    }

    @Test
    fun onPrecipitationUnitSelected_updatesRepository() = runTest {
        viewModel = SettingsViewModel(userPreferencesRepository)

        viewModel.onPrecipitationUnitSelected(PrecipitationUnit.INCH.toSettingsDescription())

        coVerify { userPreferencesRepository.updatePrecipitationUnit(PrecipitationUnit.INCH.name) }
    }

    @Test
    fun onPrecipitationUnitSelected_repositoryThrows_emitsErrorState() = runTest {
        coEvery { userPreferencesRepository.updatePrecipitationUnit(any()) } throws Exception()

        viewModel = SettingsViewModel(userPreferencesRepository)
        viewModel.onPrecipitationUnitSelected(PrecipitationUnit.INCH.toSettingsDescription())

        assertEquals(SettingsDataState.Error, viewModel.uiState.value.settingsDataState)
    }
}
