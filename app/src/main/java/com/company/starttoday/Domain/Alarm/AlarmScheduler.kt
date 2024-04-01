package com.company.starttoday.Domain.Alarm

import com.company.starttoday.Domain.Alarm.Entity.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}