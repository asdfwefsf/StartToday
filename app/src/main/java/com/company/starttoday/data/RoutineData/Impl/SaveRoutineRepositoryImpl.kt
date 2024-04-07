package com.company.starttoday.data.RoutineData.Impl

import com.company.starttoday.Domain.Routine.Model.RoutineDomain
import com.company.starttoday.Domain.Routine.Repository.SaveRoutineRepository
import com.company.starttoday.data.RoutineData.Mapper.toRoutine
import com.company.starttoday.data.RoutineData.Room.RoutineDao
import javax.inject.Inject

class SaveRoutineRepositoryImpl @Inject constructor(
    private val dao : RoutineDao
) : SaveRoutineRepository{
    override suspend fun saveRoutine(routineDomain: RoutineDomain) {
        val routine = routineDomain.toRoutine()

        dao.upsertRoutine(routine)
    }
}

