package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.Model.RoutineDomain

interface DeleteRoutineRepository {
    suspend fun deleteRoutine(routineDomain: RoutineDomain)
}