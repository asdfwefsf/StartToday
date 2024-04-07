package com.company.starttoday.Domain.Routine.UseCases

import com.company.starttoday.Domain.Routine.Model.RoutineDomain
import com.company.starttoday.Domain.Routine.Repository.DeleteRoutineRepository
import javax.inject.Inject

class DeleteRoutineUseCase @Inject constructor(
    private val deleteRoutineRepository: DeleteRoutineRepository
) {
    suspend operator fun invoke(routineDomain: RoutineDomain) {
        deleteRoutineRepository.deleteRoutine(routineDomain)
    }
}