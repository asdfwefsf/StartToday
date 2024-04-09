package com.company.starttoday.data.RoutineData.Mapper

import com.company.starttoday.data.RoutineData.Room.Routine

fun com.company.starttoday.Domain.Routine.DTO.RoutineDomain.toRoutine() : Routine {
    return Routine(
        id = this.id,
        routineTime = this.routineTime,
        routineName = this.routineName
    )
}



// DomainRoutine을 DataRoutine으로 변경