package com.company.starttoday.Domain.Alarm.Entity

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