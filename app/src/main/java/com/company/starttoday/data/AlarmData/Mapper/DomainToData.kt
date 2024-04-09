package com.company.starttoday.data.AlarmData.Mapper

import com.company.starttoday.Domain.Alarm.DTO.DomainDTO
import com.company.starttoday.data.AlarmData.Room.Alarm

fun DomainDTO.toEntity(): Alarm {
    return Alarm(
        startH = this.startH,
        startM = this.startM,
        term = this.term,
        endH = this.endH,
        endM = this.endM
    )
}