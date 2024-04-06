package com.company.starttoday.data.AlarmData.Room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Upsert
    suspend fun upsertAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarm ORDER BY id DESC")
    fun getAlarm(): Flow<Alarm?>
}