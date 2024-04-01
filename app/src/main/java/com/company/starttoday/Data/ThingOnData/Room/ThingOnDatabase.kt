package com.company.starttoday.Data.ThingOnData.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ThingOn::class],
    version = 1,
    exportSchema = false
)
abstract class ThingOnDatabase : RoomDatabase() {
    abstract val dao : ThingOnDao
}