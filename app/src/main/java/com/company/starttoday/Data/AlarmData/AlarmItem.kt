package com.company.starttoday.Data.AlarmData

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String,
    var startH: Int,
    var startM: Int,
    var term: Int,
    var endH: Int,
    var endM: Int
)