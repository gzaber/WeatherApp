package com.gzaber.weatherapp.data.repository.settings

import com.gzaber.weatherapp.data.repository.locations.model.Location
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun updateLocation(location: Location)

    fun observeLocation(): Flow<Location>
}