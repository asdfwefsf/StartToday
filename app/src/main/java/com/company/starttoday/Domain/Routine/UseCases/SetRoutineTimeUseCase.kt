package com.company.starttoday.Domain.Routine.UseCases

import com.company.starttoday.data.RoutineData.Room.Routine
import com.company.starttoday.Domain.Routine.Model.RoutineType
import com.company.starttoday.Domain.Routine.Repository.SetRoutineTimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetRoutineTimeUseCase @Inject constructor(
    private val setRoutineTimeRepository: SetRoutineTimeRepository
) {
    suspend operator fun invoke(routineType : RoutineType) : Flow<List<Routine>> {
        return setRoutineTimeRepository.setRoutineTime(routineType)
    }
}