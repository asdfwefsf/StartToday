package com.company.starttoday.data.RoutineData.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Upsert
    suspend fun upsertRoutine(routine : Routine)

    @Delete
    suspend fun deleteRoutine(routine: Routine)

    @Query("SELECT * FROM routine WHERE routineTime = :timeType ORDER BY routineName ASC")
    fun getRotuineTimeToday(timeType: String): Flow<List<Routine>>
}