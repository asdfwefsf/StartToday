package com.company.starttoday.data.RoutineData.Mapper

import com.company.starttoday.Domain.Routine.DTO.RoutineDomain
import com.company.starttoday.data.RoutineData.Room.Routine

fun Routine.toDomainModel(): RoutineDomain {
    return RoutineDomain(
        id = this.id,
        routineTime = this.routineTime,
        routineName = this.routineName
    )
}