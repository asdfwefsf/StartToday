package com.company.starttoday.Domain.Routine.UseCases

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain
import com.company.starttoday.Domain.Routine.Repository.SaveRoutineRepository
import javax.inject.Inject

class SaveRoutineUseCase @Inject constructor(
    private val saveRoutineRepository : SaveRoutineRepository
) {
    suspend operator fun invoke(routineDomain : RoutineDomain) {
        saveRoutineRepository.saveRoutine(routineDomain)
    }
}