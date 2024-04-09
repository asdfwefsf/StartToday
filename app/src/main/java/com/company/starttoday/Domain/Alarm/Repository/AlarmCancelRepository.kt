package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.Model.DomainDTO

interface AlarmCancelRepository {
    suspend fun cancel(item: DomainDTO)
}