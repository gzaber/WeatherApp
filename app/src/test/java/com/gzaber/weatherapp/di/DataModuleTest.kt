package com.gzaber.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.test.core.app.ApplicationProvider
import com.gzaber.weatherapp.UserPreferences
import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.data.source.local.LocationsDatabase
import com.gzaber.weatherapp.data.source.network.location.LocationApi
import com.gzaber.weatherapp.data.source.network.weather.WeatherApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.verify.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@OptIn(KoinExperimentalAPI::class)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DataModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        modules(
            dataModule,
            viewModelModule
        )
    }

    @Test
    fun verifyDataModule() {
        dataModule.verify(
            extraTypes = listOf(
                Context::class
            )
        )
    }

    @Test
    fun dataModule_LocationsDatabaseRequested_providesInstance() {
        val database = get<LocationsDatabase>()

        assertNotNull(database)
    }

    @Test
    fun dataModule_LocationApiRequested_providesInstance() {
        val api = get<LocationApi>()

        assertNotNull(api)
    }

    @Test
    fun dataModule_WeatherApiRequested_providesInstance() {
        val api = get<WeatherApi>()

        assertNotNull(api)
    }

    @Test
    fun dataModule_LocationsRepositoryRequested_providesInstance() {
        val repository = get<LocationsRepository>()

        assertNotNull(repository)
    }

    @Test
    fun dataModule_WeatherRepositoryRequested_providesInstance() {
        val repository = get<WeatherRepository>()

        assertNotNull(repository)
    }

    @Test
    fun dataModule_DataStoreRequested_providesInstance() {
        val dataStore = get<DataStore<UserPreferences>>()

        assertNotNull(dataStore)
    }

    @Test
    fun dataModule_UserPreferencesRepositoryRequested_providesInstance() {
        val repository = get<UserPreferencesRepository>()

        assertNotNull(repository)
    }
}
