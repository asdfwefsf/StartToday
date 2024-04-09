package com.company.starttoday.Domain.Alarm.Repository

import com.company.starttoday.Domain.Alarm.DTO.DomainDTO
import kotlinx.coroutines.flow.Flow

interface UpdateAlarmRepository {
    suspend fun getAlarmData() : Flow<DomainDTO>
}