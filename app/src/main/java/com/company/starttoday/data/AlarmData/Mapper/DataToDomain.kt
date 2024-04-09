package com.company.starttoday.data.AlarmData.Mapper

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.data.AlarmData.Room.Alarm
import java.time.LocalDateTime

fun Alarm.toDomainDTO(): DomainDTO {
    return DomainDTO(
        time = LocalDateTime.now(),
        startH = this.startH,
        startM = this.startM,
        term = this.term,
        endH = this.endH,
        endM = this.endM
    )
}