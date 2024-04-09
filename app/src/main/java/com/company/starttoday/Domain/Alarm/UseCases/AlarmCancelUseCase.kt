package com.company.starttoday.Domain.Alarm.UseCases

import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.AlarmCancelRepository
import javax.inject.Inject

class AlarmCancelUseCase @Inject constructor (
    private val alarmCancelRepository: AlarmCancelRepository
) {
    suspend operator fun invoke(item : DomainDTO) {
        alarmCancelRepository.cancel(item)
    }
}