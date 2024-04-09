package com.company.starttoday.Domain.Routine

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain
import com.company.starttoday.Domain.Routine.DTO.RoutineType

sealed interface RoutineEvent {
    object SaveRoutine : RoutineEvent
    object ShowDialog : RoutineEvent
    object HideDialog: RoutineEvent

    data class SetRoutineTime(val routineTime : String) : RoutineEvent
    data class SetRoutineName(val routineName : String) : RoutineEvent
    data class RoutineTimeType(val routineType: RoutineType) : RoutineEvent
    data class DeleteRoutine(val routine : RoutineDomain): RoutineEvent
}




