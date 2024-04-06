package com.company.starttoday.Domain.Routine.Entity

import com.company.starttoday.data.RoutineData.Room.Routine

data class RoutineState(
    val routines: List<Routine> = emptyList(),
    val routineTime: String = "",
    val routineName: String = "",
    val isAddingContact: Boolean = false,
    val sortType: String = ""
)