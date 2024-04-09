package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.Model.DomainDTO

interface AlarmScheduleRepository {
    suspend fun schedule(item: DomainDTO)
}