package com.company.starttoday.Presentation.Alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.company.starttoday.data.AlarmData.MediaPlayerManager
import com.company.starttoday.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        var startHour = ""
        var startMinute = ""
        var alarmTerm = 0

        var endHour = ""
        var endMinute = ""

        // setAlarmScreen에서 설정한 알람과 관련된 숫자들 모임
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        startHour = intent.getStringExtra("startH").toString()
        startMinute = intent.getStringExtra("startM").toString()
        alarmTerm = intent.getIntExtra("term" , 0)
        endHour = intent.getStringExtra("endH").toString()
        endMinute = intent.getStringExtra("endM").toString()

        // 현재 시간
        var formatter = DateTimeFormatter.ofPattern("HH:mm")
        var currentTime = LocalTime.now().toString()
        var currentTimeHourMinute = currentTime.format(formatter).split(":")
        var currentHour = currentTimeHourMinute.get(0).trim()
        var currentMinute = currentTimeHourMinute.get(1).trim()
        Log.d("localTime : " , startHour)
        Log.d("localTime : " , startMinute)
        Log.d("localTime : " , "currentHour : $currentHour")
        Log.d("localTime : " , endHour)
        Log.d("localTime : " , endMinute)



        // 알림 코드 시작
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "alarm_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(notificationChannelId, "Alarm Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val cancelIntent = Intent(context , AlarmCancelReceiver ::class.java).apply {
            // 알람 취소 액션 식별자 추가
            action = "stopMusic"
        }
        val cancelPendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(context, notificationChannelId)
            .setSmallIcon(R.drawable.confirmbutton) // 알림 아이콘 설정
            .setContentTitle("기상 시간 입니다.") // 알림 제목
//            .setContentText("Your alarm message: $message") // 알림 내용
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.add_icon, "알람 해제", cancelPendingIntent) // 알림에 알람 취소 버튼 추가
            .setAutoCancel(true)

        if(context != null && mediaPlayer == null) {
            MediaPlayerManager.getMediaPlayer(context)
        }


        // 백그라운드에서 알람 관찰하기
        val IoScope = CoroutineScope(Dispatchers.IO)
        val musicScope = CoroutineScope(Dispatchers.Main)

        when(intent.action) {
            "startMusic" ->

                IoScope.launch {

                    while (isActive) {
                        formatter = DateTimeFormatter.ofPattern("HH:mm")
                        currentTime = LocalTime.now().toString()
                        currentTimeHourMinute = currentTime.format(formatter).split(":")
                        currentHour = currentTimeHourMinute.get(0).trim()
                        currentMinute = currentTimeHourMinute.get(1).trim()

                        if(startHour == currentHour && startMinute == currentMinute) {
                            musicScope.launch {
                                repeat(alarmTerm) {
                                    MediaPlayerManager.startMusic()
                                    delay(alarmTerm.toLong() * 1000 * 60)

                                }

                            }
                            notificationManager.notify(1, notificationBuilder.build())
                        }
                    }

                }





        }
        //

    }


}