package com.company.starttoday.Data.ImageLinkData.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageLinkDao {

    // Upsert -> Insert 대체
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertImageLink(imageLink: ImageLink)

    @Query("SELECT * from imagelink ORDER BY id DESC LIMIT 5")
    fun getImageLink() : Flow<List<ImageLink>>
}