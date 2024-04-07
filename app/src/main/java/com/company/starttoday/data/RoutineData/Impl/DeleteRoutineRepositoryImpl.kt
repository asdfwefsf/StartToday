package com.company.starttoday.data.RoutineData.Impl

import com.company.starttoday.Domain.Routine.Model.RoutineDomain
import com.company.starttoday.Domain.Routine.Repository.DeleteRoutineRepository
import com.company.starttoday.data.RoutineData.Mapper.toRoutine
import com.company.starttoday.data.RoutineData.Room.RoutineDao
import javax.inject.Inject

class DeleteRoutineRepositoryImpl @Inject constructor(
    private val dao : RoutineDao
) : DeleteRoutineRepository{
    override suspend fun deleteRoutine(routineDomain: RoutineDomain) {
        val routine = routineDomain.toRoutine()
        dao.deleteRoutine(routine)
    }
}