package com.company.starttoday.Data.RoutineData

import com.company.starttoday.Data.RoutineData.Room.Routine

data class RoutineState(
    val routines: List<Routine> = emptyList(),
    val routineTime: String = "",
    val routineName: String = "",
    val isAddingContact: Boolean = false,
    val sortType: String = ""
)
