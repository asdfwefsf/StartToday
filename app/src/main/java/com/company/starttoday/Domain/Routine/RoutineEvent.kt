package com.company.starttoday.Domain.Routine

import com.company.starttoday.Data.RoutineData.Room.Routine
import com.company.starttoday.Data.RoutineData.RoutineType

sealed interface RoutineEvent {
    object SaveRoutine : RoutineEvent
    object ShowDialog : RoutineEvent
    object HideDialog: RoutineEvent

    data class SetRoutineTime(val routineTime : String) : RoutineEvent
    data class SetRoutineName(val routineName : String) : RoutineEvent
    data class RoutineTimeType(val routineType: RoutineType) : RoutineEvent
    data class DeleteRoutine(val routine : Routine): RoutineEvent

}