package com.gzaber.weatherapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class LocationsDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}