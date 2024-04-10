package com.company.starttoday.Domain.Alarm.DTO

import java.time.LocalDateTime

data class DomainDTO(
    val time: LocalDateTime,
    var startH: Int,
    var startM: Int,
    var term: Int,
    var endH: Int,
    var endM: Int
)