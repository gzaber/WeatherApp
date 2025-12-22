package com.gzaber.weatherapp.di

import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.ui.search.SearchViewModel
import com.gzaber.weatherapp.ui.settings.SettingsViewModel
import com.gzaber.weatherapp.ui.weather.WeatherViewModel
import com.gzaber.weatherapp.util.KoinTestRule
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.verify.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@OptIn(KoinExperimentalAPI::class)
@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class ViewModelModuleTest : KoinTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val koinTestRule = KoinTestRule(modules = listOf(dataModule, viewModelModule))

    @Test
    fun verifyViewModelModule() {
        viewModelModule.verify(
            extraTypes = listOf(
                WeatherViewModel::class,
                SearchViewModel::class,
                SettingsViewModel::class,
                WeatherRepository::class,
                LocationsRepository::class,
                UserPreferencesRepository::class
            )
        )
    }

    @Test
    fun viewModelModule_WeatherViewModelRequested_providesInstance() {
        val viewModel = get<WeatherViewModel>()

        assertNotNull(viewModel)
    }

    @Test
    fun viewModelModule_SearchViewModelRequested_providesInstance() {
        val viewModel = get<SearchViewModel>()

        assertNotNull(viewModel)
    }

    @Test
    fun viewModelModule_SettingsViewModelRequested_providesInstance() {
        val viewModel = get<SettingsViewModel>()

        assertNotNull(viewModel)
    }
}
