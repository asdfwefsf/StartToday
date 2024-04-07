package com.company.starttoday.Domain.Routine.Entity

import com.company.starttoday.Domain.Routine.Model.RoutineDomain

data class RoutineState(
    val routines: List<RoutineDomain> = emptyList(),
    val routineTime: String = "",
    val routineName: String = "",
    val isAddingContact: Boolean = false,
    val sortType: String = ""
)