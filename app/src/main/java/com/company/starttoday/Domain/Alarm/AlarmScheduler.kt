package com.company.starttoday.Domain.Alarm

import com.company.starttoday.Data.AlarmData.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}