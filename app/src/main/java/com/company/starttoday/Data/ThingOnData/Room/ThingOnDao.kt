package com.company.starttoday.Data.ThingOnData.Room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingOnDao {
    @Update
    suspend fun updateAll(thingOnList : List<ThingOn>)

    @Query("DELETE from ThingOn")
    fun deleteAll()


    @Upsert
    suspend fun upsertStrings(strings: ThingOn)

    @Query("SELECT * from ThingOn ORDER BY id DESC LIMIT 5")
    fun getAll() : Flow<List<ThingOn>>

    @Query("SELECT * from ThingOn ORDER BY id DESC LIMIT 5")
    fun getAllList() : Flow<List<ThingOn>>


}