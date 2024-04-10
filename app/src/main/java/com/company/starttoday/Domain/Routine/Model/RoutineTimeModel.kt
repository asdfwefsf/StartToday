package com.company.starttoday.Domain.Routine.Model

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain

data class RoutineState(
    val routines: List<RoutineDomain> = emptyList(),
    val routineTime: String = "",
    val routineName: String = "",
    val isAddingContact: Boolean = false,
    val sortType: String = ""
)



