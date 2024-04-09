package com.company.starttoday.Domain.Alarm.UseCases

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.AlarmScheduleRepository
import javax.inject.Inject

class AlarmScheduleUseCase @Inject constructor(
    private val alarmSheduleRepository: AlarmScheduleRepository,
) {
    suspend operator fun invoke(alarmItem : DomainDTO) {
        alarmSheduleRepository.schedule(alarmItem)
    }
}