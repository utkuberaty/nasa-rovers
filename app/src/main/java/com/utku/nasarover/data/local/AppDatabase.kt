package com.utku.nasarover.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utku.nasarover.data.entities.Camera
import com.utku.nasarover.data.entities.Photos
import com.utku.nasarover.data.entities.Rover

@Database(
    entities = [Photos::class, Camera::class, Rover::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nasaRoverDao(): NasaRoverDao
}