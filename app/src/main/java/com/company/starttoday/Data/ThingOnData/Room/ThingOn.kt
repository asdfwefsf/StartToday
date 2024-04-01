package com.company.starttoday.Data.ThingOnData.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ThingOn (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
//    val imageLink : String,
    val thingOn : String
)