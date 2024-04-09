package com.company.starttoday.Presentation.Alarm

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.company.starttoday.data.AlarmData.MediaPlayerManager

class AlarmCancelReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.cancel(1)

        if(intent?.action == "stopMusic") {
            MediaPlayerManager.stopMusic()
        }
    }
}