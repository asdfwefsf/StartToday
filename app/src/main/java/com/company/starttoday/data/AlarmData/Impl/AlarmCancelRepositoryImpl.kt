package com.company.starttoday.data.AlarmData.Impl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.AlarmCancelRepository
import com.company.starttoday.Presentation.Alarm.AlarmCancelReceiver
import com.company.starttoday.Presentation.Alarm.AlarmReceiver
import javax.inject.Inject

class AlarmCancelRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    private val context : Context
) : AlarmCancelRepository {
    override suspend fun cancel(item : DomainDTO) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("startH", String.format("%02d", item.startH))
            putExtra("startM", String.format("%02d", item.startM))
            putExtra("term", item.term)
            putExtra("endH", String.format("%02d", item.endH))
            putExtra("endM", String.format("%02d", item.endM))
            action = "stopMusic"
        }
        alarmManager.cancel(


            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmCancelReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }
}