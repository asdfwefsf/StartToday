package com.company.starttoday.Domain.Alarm

import com.company.starttoday.Domain.Alarm.Model.DomainDTO

interface AlarmScheduler {
    fun schedule(item: DomainDTO)
    fun cancel(item: DomainDTO)
}