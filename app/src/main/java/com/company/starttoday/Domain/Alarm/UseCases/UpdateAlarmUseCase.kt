package com.company.starttoday.Domain.Alarm.UseCases

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.UpdateAlarmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAlarmUseCase @Inject constructor(
    private val updateAlarmRepository: UpdateAlarmRepository
) {
    suspend operator fun invoke() : Flow<DomainDTO> {
        return updateAlarmRepository.getAlarmData()
    }
}