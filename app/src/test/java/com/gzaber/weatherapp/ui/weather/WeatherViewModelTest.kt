package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.ui.util.defaultLocationPreferences
import com.gzaber.weatherapp.ui.util.defaultWeatherUnitsPreferences
import com.gzaber.weatherapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var viewModel: WeatherViewModel

    private val location = LocationPreferences(52.23, 21.01, "Warsaw", "Poland")
    private val units = WeatherUnitsPreferences(
        TemperatureUnit.CELSIUS.name, WindSpeedUnit.KILOMETERS_PER_HOUR.name,
        PrecipitationUnit.MILLIMETER.name
    )
    private val currentWeather = mockk<CurrentWeather>(relaxed = true)
    private val hourlyWeather = mockk<HourlyWeather>(relaxed = true)
    private val dailyWeather = mockk<DailyWeather>(relaxed = true)

    @Before
    fun setUp() {
        weatherRepository = mockk(relaxed = true)
        userPreferencesRepository = mockk(relaxed = true)

        every { userPreferencesRepository.observeLocation() } returns flowOf(location)
        every { userPreferencesRepository.observeWeatherUnits() } returns flowOf(units)

        coEvery { weatherRepository.getCurrentWeather(any(), any(), any()) } returns currentWeather
        coEvery { weatherRepository.getHourlyWeather(any(), any(), any()) } returns hourlyWeather
        coEvery { weatherRepository.getDailyWeather(any(), any(), any()) } returns dailyWeather
    }

    @Test
    fun init_usesLocationDefaultEmptyValues_emitsLoadingState() = runTest {
        every { userPreferencesRepository.observeLocation() } returns flowOf(
            defaultLocationPreferences()
        )
        every { userPreferencesRepository.observeWeatherUnits() } returns flowOf(
            defaultWeatherUnitsPreferences()
        )

        viewModel = WeatherViewModel(weatherRepository, userPreferencesRepository)

        assertEquals(WeatherDataState.Loading, viewModel.uiState.value.weatherDataState)
    }

    @Test
    fun init_usesLocationAndUnitsFromRepository_emitsSuccessStateWithWeatherData() = runTest {
        viewModel = WeatherViewModel(weatherRepository, userPreferencesRepository)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(viewModel.uiState.value.weatherDataState is WeatherDataState.Success)
        val successState = state.weatherDataState as WeatherDataState.Success
        assertEquals(currentWeather, successState.currentWeather)
        assertEquals(hourlyWeather, successState.hourlyWeather)
        assertEquals(dailyWeather, successState.dailyWeather)
        assertEquals(location, state.locationPreferences)
        assertEquals(units, state.weatherUnitsPreferences)
    }

    @Test
    fun init_userPreferencesRepositoryThrows_emitsErrorState() = runTest {
        every { userPreferencesRepository.observeLocation() } returns flowOf(location).onStart { throw Exception() }

        viewModel = WeatherViewModel(weatherRepository, userPreferencesRepository)

        assertEquals(WeatherDataState.Error, viewModel.uiState.value.weatherDataState)
    }

    @Test
    fun init_weatherRepositoryThrows_emitsErrorState() = runTest {
        coEvery { weatherRepository.getCurrentWeather(any(), any(), any()) } throws Exception()

        viewModel = WeatherViewModel(weatherRepository, userPreferencesRepository)

        assertEquals(WeatherDataState.Error, viewModel.uiState.value.weatherDataState)
    }
}
