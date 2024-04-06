package com.company.starttoday.Domain.Routine.Repository

import com.company.starttoday.Domain.Routine.Entity.Routine

interface SaveRoutineRepository {
    suspend fun saveRoutine(routine : Routine)
}