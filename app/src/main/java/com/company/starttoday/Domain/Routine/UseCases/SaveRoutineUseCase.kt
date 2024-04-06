package com.company.starttoday.Domain.Routine.UseCases

import com.company.starttoday.Domain.Routine.Entity.Routine
import com.company.starttoday.Domain.Routine.Repository.SaveRoutineRepository
import javax.inject.Inject

class SaveRoutineUseCase @Inject constructor(
    private val saveRoutineRepository : SaveRoutineRepository
) {
    suspend operator fun invoke(routine : Routine) {
        saveRoutineRepository.saveRoutine(routine)
    }
}