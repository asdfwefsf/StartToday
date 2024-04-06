package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Data.RoutineData.Room.Routine
import com.company.starttoday.Domain.Routine.Model.RoutineType
import kotlinx.coroutines.flow.Flow

interface SetRoutineTimeRepository {
    suspend fun setRoutineTime(routineType: RoutineType) : Flow<List<Routine>>
}