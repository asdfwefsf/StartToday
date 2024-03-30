package com.company.starttoday.Presentation.Alarm

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.company.starttoday.Data.AlarmData.MediaPlayerManager

class AlarmCancelReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.cancel(1)

        // 알람 취소 시 MainActivity 재시작
//        val restartIntent = Intent(context, MainActivity::class.java)
//
//            .apply {
//            // 새로운 작업으로 시작
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        context?.startActivity(restartIntent)

        if(intent?.action == "stopMusic") {
            MediaPlayerManager.stopMusic()
//            context?.startActivity(restartIntent)

        }
    }
}