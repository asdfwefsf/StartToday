package com.company.starttoday.data.AlarmData.Impl

import com.company.starttoday.Domain.Alarm.DTO.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.UpdateAlarmRepository
import com.company.starttoday.data.AlarmData.Mapper.toDomainDTO
import com.company.starttoday.data.AlarmData.Room.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateAlarmRepositoryImpl @Inject constructor(
    private val dao : AlarmDao
) : UpdateAlarmRepository{
    override suspend fun getAlarmData() : Flow<DomainDTO> {


        return dao.getAlarm().map { alarmData->
            alarmData.toDomainDTO()
        }
    }
}
