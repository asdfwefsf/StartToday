package com.company.starttoday.data.AlarmData.Impl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.Repository.AlarmScheduleRepository
import com.company.starttoday.Presentation.Alarm.AlarmReceiver
import com.company.starttoday.data.AlarmData.Mapper.toEntity
import com.company.starttoday.data.AlarmData.Room.AlarmDao
import java.time.ZoneId
import javax.inject.Inject

class AlarmScheduleRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    private val context : Context,
    private val dao : AlarmDao
) : AlarmScheduleRepository {
    override suspend fun schedule(item: DomainDTO) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("startH", String.format("%02d", item.startH))
            putExtra("startM", String.format("%02d", item.startM))
            putExtra("term", item.term)
            putExtra("endH", String.format("%02d", item.endH))
            putExtra("endM", String.format("%02d", item.endM))
            action = "startMusic"
        }
        Log.d("gonee" , "Impl OK")
        Log.d("gonee" , item.startH.toString())
        Log.d("gonee" , item.startM.toString())
        Log.d("gonee" , item.term.toString())
        Log.d("gonee" , item.endH.toString())
        Log.d("gonee" , item.endM.toString())
        Log.d("gonee" , item.time.toString())

        val zonedDateTime = item.time.atZone(ZoneId.systemDefault())
        Log.d("gonee" , zonedDateTime.toString())

        dao.upsertAlarm(item.toEntity())




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
}