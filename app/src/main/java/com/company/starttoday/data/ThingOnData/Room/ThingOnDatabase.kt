package com.company.starttoday.data.ThingOnData.Room

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