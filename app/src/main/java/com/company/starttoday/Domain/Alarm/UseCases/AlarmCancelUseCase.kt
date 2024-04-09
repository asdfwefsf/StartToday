package com.company.starttoday.Domain.Alarm.UseCases

import com.company.starttoday.Domain.Alarm.Repository.AlarmCancelRepository
import javax.inject.Inject

class AlarmCancelUseCase @Inject constructor (
    private val alarmCancelRepository: AlarmCancelRepository
) {
}