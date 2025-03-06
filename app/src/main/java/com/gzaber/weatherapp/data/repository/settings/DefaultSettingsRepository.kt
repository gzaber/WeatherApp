package com.gzaber.weatherapp.data.repository.settings

import androidx.datastore.core.DataStore
import com.gzaber.weatherapp.Settings
import com.gzaber.weatherapp.data.repository.locations.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultSettingsRepository(
    private val settingsStore: DataStore<Settings>
) : SettingsRepository {

    override suspend fun updateLocation(location: Location) {
        settingsStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setName(location.name)
                .setLatitude(location.latitude)
                .setLongitude(location.longitude)
                .setDescription(location.description)
                .build()
        }
    }

    override fun observeLocation(): Flow<Location> =
        settingsStore.data.map {
            Location(
                id = "",
                name = it.name,
                latitude = it.latitude,
                longitude = it.longitude,
                description = it.description
            )
        }
}