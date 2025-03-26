package com.gzaber.weatherapp.data.repository.userpreferences

import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
import com.gzaber.weatherapp.data.repository.userpreferences.model.WeatherUnitsPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    suspend fun updateLocation(
        latitude: Double,
        longitude: Double,
        name: String,
        description: String
    )

    suspend fun updateTemperatureUnit(temperatureUnit: String)

    suspend fun updateWindSpeedUnit(windSpeedUnit: String)

    suspend fun updatePrecipitationUnit(precipitationUnit: String)

    fun observeLocation(): Flow<LocationPreferences>

    fun observeWeatherUnits(): Flow<WeatherUnitsPreferences>
}