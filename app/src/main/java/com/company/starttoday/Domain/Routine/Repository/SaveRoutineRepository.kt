package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain


interface SaveRoutineRepository {
    suspend fun saveRoutine(routineDomain : RoutineDomain)
}