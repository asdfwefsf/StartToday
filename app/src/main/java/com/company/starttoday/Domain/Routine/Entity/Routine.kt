package com.company.starttoday.Domain.Routine.Entity

import com.company.starttoday.Domain.Routine.Model.RoutineType

data class Routine(
    val id : Int? = null,
    val routineTime : String,
    val routineName : String,
    val routineType : RoutineType
) {
}