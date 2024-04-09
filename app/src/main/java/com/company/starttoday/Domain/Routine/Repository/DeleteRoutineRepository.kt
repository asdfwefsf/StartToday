package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain

interface DeleteRoutineRepository {
    suspend fun deleteRoutine(routineDomain: RoutineDomain)
}