package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.Model.RoutineDomain
import com.company.starttoday.Domain.Routine.Model.RoutineType
import kotlinx.coroutines.flow.Flow

interface SetRoutineTimeRepository {
    suspend fun setRoutineTime(routineType: RoutineType) : Flow<List<RoutineDomain>>
}