package com.company.starttoday.Data.RoutineData

data class RoutineState(
    val routines : List<Routine> = emptyList(),
    val routineTime : String = "",
    val routineName : String = "",
    val isAddingContact: Boolean = false,
    val sortType : String = ""
    )
