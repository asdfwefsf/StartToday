package com.company.starttoday.Data.RoutineData.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Routine::class],
    version = 1,
    exportSchema = false
)
abstract class RoutineDatabase : RoomDatabase() {
    abstract val dao : RoutineDao
}