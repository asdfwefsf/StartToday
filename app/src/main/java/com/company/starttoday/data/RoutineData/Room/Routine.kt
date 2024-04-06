package com.company.starttoday.data.RoutineData.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val routineTime : String,
    val routineName : String
)
