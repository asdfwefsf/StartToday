package com.company.starttoday.Data.ImageLinkData

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageLinkDao {

    @Upsert
    suspend fun upsertImageLink(imageLink: ImageLink)

    @Query("SELECT * from imagelink ORDER BY id DESC LIMIT 5")
    fun getImageLink() : Flow<List<ImageLink>>
}