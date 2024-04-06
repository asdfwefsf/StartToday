package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.Model.RoutineDomain


interface SaveRoutineRepository {
    suspend fun saveRoutine(routineDomain : RoutineDomain)
}