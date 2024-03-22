package com.company.starttoday.Data.RoutineData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val routineTime : String,
    val routineName : String
)
