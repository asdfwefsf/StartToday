package com.company.starttoday.Data.AlarmData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val startH : Int,
    val startM : Int,
    val term : Int,
    val endH : Int,
    val endM : Int
)