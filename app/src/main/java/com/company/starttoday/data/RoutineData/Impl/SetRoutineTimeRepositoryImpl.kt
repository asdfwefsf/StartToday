package com.company.starttoday.data.RoutineData.Impl

import com.company.starttoday.data.RoutineData.Room.Routine
import com.company.starttoday.data.RoutineData.Room.RoutineDao
import com.company.starttoday.Domain.Routine.Model.RoutineType
import com.company.starttoday.Domain.Routine.Repository.SetRoutineTimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetRoutineTimeRepositoryImpl @Inject constructor(
    private val dao : RoutineDao
) : SetRoutineTimeRepository {
    override suspend fun setRoutineTime(routineType: RoutineType): Flow<List<Routine>> {
        return when(routineType) {
            RoutineType.TODAY -> dao.getRotuineTimeToday("일간")
            RoutineType.WEEK -> dao.getRotuineTimeToday("주간")
            RoutineType.MONTH -> dao.getRotuineTimeToday("월간")
            RoutineType.YEAR -> dao.getRotuineTimeToday("연간")
        }
    }
}