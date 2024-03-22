package com.company.starttoday.Data.ImageLinkData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageLink(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val imageLink : String
)
