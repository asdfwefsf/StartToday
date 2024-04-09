package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.DTO.DomainDTO

interface AlarmScheduleRepository {
    suspend fun schedule(item: DomainDTO)
}