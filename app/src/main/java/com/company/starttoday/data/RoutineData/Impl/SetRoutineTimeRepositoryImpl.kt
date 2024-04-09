package com.company.starttoday.data.RoutineData.Impl

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain
import com.company.starttoday.Domain.Routine.DTO.RoutineType
import com.company.starttoday.Domain.Routine.Repository.SetRoutineTimeRepository
import com.company.starttoday.data.RoutineData.Mapper.toDomainModel
import com.company.starttoday.data.RoutineData.Room.RoutineDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SetRoutineTimeRepositoryImpl @Inject constructor(
    private val dao : RoutineDao
) : SetRoutineTimeRepository {


    override suspend fun setRoutineTime(routineType: RoutineType): Flow<List<RoutineDomain>> {
        // Routine을 반환하도록 해야해.
        val timeType = when(routineType) {
            RoutineType.TODAY -> "일간"
            RoutineType.WEEK -> "주간"
            RoutineType.MONTH -> "월간"
            RoutineType.YEAR -> "연간"
        }
        // RoutineDao의 반환값을 RoutineDomain 객체 리스트로 변환
        return dao.getRotuineTimeToday(timeType).map { routines ->
            routines.map { routine ->
                routine.toDomainModel()
            }
        }
    }
}