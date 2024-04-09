package com.company.starttoday.data.AlarmData.Impl

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.AlarmCancelRepository
import javax.inject.Inject

class AlarmCancelRepositoryImpl @Inject constructor(

) : AlarmCancelRepository {
    override suspend fun cancel(item: DomainDTO) {
        TODO("Not yet implemented")
    }
}