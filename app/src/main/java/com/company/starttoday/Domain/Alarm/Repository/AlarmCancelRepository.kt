package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.DTO.DomainDTO

interface AlarmCancelRepository {
    suspend fun cancel(item : DomainDTO)
}