package com.company.starttoday.Data.AlarmData

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.company.starttoday.Domain.Alarm.AlarmScheduler
import com.company.starttoday.Domain.Alarm.Entity.AlarmItem
import com.company.starttoday.Presentation.Alarm.AlarmReceiver
import java.time.ZoneId
import javax.inject.Inject

class AndroidAlarmScheduler @Inject constructor(
    private val context: Context,
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("startH", String.format("%02d", item.startH))
            putExtra("startM", String.format("%02d", item.startM))
            putExtra("term", item.term)
            putExtra("endH", String.format("%02d", item.endH))
            putExtra("endM", String.format("%02d", item.endM))

//            putExtra("startH", String.format("%02d", item.startH))
//            putExtra("startM", String.format("%02d", item.startM))
//            putExtra("term", item.term.toString())
//            putExtra("endH", String.format("%02d", item.endH))
//            putExtra("endM", String.format("%02d", item.endM))
            putExtra("EXTRA_MESSAGE", item.message)
            action = "startMusic"
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }
}