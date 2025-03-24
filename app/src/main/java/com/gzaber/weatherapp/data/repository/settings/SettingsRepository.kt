package com.gzaber.weatherapp.data.repository.settings

import com.gzaber.weatherapp.data.repository.settings.model.LocationSettings
import com.gzaber.weatherapp.data.repository.settings.model.WeatherUnitsSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun updateLocation(
        latitude: Double,
        longitude: Double,
        name: String,
        description: String
    )

    suspend fun updateWeatherUnits(
        temperatureUnit: String,
        windSpeedUnit: String,
        precipitationUnit: String
    )

    fun observeLocation(): Flow<LocationSettings>

    fun observeWeatherUnits(): Flow<WeatherUnitsSettings>
}