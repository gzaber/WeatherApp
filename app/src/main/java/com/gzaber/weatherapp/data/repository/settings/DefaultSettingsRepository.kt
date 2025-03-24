package com.gzaber.weatherapp.data.repository.settings

import androidx.datastore.core.DataStore
import com.gzaber.weatherapp.Settings
import com.gzaber.weatherapp.data.repository.settings.model.LocationSettings
import com.gzaber.weatherapp.data.repository.settings.model.WeatherUnitsSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultSettingsRepository(
    private val settingsStore: DataStore<Settings>
) : SettingsRepository {

    override suspend fun updateLocation(
        latitude: Double,
        longitude: Double,
        name: String,
        description: String
    ) {
        settingsStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setName(name)
                .setDescription(description)
                .build()
        }
    }

    override suspend fun updateWeatherUnits(
        temperatureUnit: String,
        windSpeedUnit: String,
        precipitationUnit: String
    ) {
        settingsStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setTemperatureUnit(temperatureUnit)
                .setWindSpeedUnit(windSpeedUnit)
                .setPrecipitationUnit(precipitationUnit)
                .build()
        }
    }

    override fun observeLocation(): Flow<LocationSettings> = settingsStore.data.map {
        LocationSettings(
            latitude = it.latitude,
            longitude = it.longitude,
            name = it.name,
            description = it.description
        )
    }

    override fun observeWeatherUnits(): Flow<WeatherUnitsSettings> = settingsStore.data.map {
        WeatherUnitsSettings(
            temperatureUnit = it.temperatureUnit,
            windSpeedUnit = it.windSpeedUnit,
            precipitationUnit = it.precipitationUnit
        )
    }
}