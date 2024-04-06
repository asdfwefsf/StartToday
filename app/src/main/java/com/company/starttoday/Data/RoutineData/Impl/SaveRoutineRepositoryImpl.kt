package com.company.starttoday.Data.RoutineData.Impl

import com.company.starttoday.Data.RoutineData.Room.RoutineDao
import com.company.starttoday.Domain.Routine.Entity.Routine
import com.company.starttoday.Domain.Routine.Repository.SaveRoutineRepository
import javax.inject.Inject

class SaveRoutineRepositoryImpl @Inject constructor(
    private val dao : RoutineDao
) : SaveRoutineRepository{
    override suspend fun saveRoutine(routine: Routine) {
        TODO("Not yet implemented")
    }
}