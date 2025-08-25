package com.gzaber.weatherapp.data.repository.userpreferences

import androidx.datastore.core.DataStore
import com.gzaber.weatherapp.UserPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultUserPreferencesRepository(
    private val settingsStore: DataStore<UserPreferences>
) : UserPreferencesRepository {

    override suspend fun updateLocation(
        latitude: Double,
        longitude: Double,
        name: String,
        country: String
    ) {
        settingsStore.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setName(name)
                .setCountry(country)
                .build()
        }
    }

    override suspend fun updateTemperatureUnit(temperatureUnit: String) {
        settingsStore.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setTemperatureUnit(temperatureUnit)
                .build()
        }
    }

    override suspend fun updateWindSpeedUnit(windSpeedUnit: String) {
        settingsStore.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setWindSpeedUnit(windSpeedUnit)
                .build()
        }
    }

    override suspend fun updatePrecipitationUnit(precipitationUnit: String) {
        settingsStore.updateData { currentPreferences ->
            currentPreferences.toBuilder()
                .setPrecipitationUnit(precipitationUnit)
                .build()
        }
    }

    override fun observeLocation(): Flow<LocationPreferences> = settingsStore.data.map {
        LocationPreferences(
            latitude = it.latitude,
            longitude = it.longitude,
            name = it.name,
            country = it.country
        )
    }

    override fun observeWeatherUnits(): Flow<WeatherUnitsPreferences> = settingsStore.data.map {
        WeatherUnitsPreferences(
            temperatureUnit = it.temperatureUnit,
            windSpeedUnit = it.windSpeedUnit,
            precipitationUnit = it.precipitationUnit
        )
    }
}