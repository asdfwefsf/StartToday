package com.company.starttoday.data.ImageLinkData.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ImageLink::class],
    version = 1,
    exportSchema = false
)
abstract class ImageLinkDatabase : RoomDatabase() {
    abstract val dao : ImageLinkDao
}