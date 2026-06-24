package com.ahwan.interntrack.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ApplicationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun applicationDao(): ApplicationDao
}