package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import kotlinx.coroutines.flow.Flow

interface UpdateAlarmRepository {
    suspend fun getAlarmData() : Flow<DomainDTO>
}