package com.company.starttoday.Data.AlarmData

//data class AlarmItem(
//    val time: LocalDateTime,
//    val message: String,
//    var startH: Int,
//    var startM: Int,
//    var term: Int,
//    var endH: Int,
//    var endM: Int
//)

//interface AlarmScheduler {
//    fun schedule(item: AlarmItem)
//    fun cancel(item: AlarmItem)
//}




//class AlarmReceiver : BroadcastReceiver() {
//    companion object {
//        var mediaPlayer: MediaPlayer? = null
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        var startHour = ""
//        var startMinute = ""
//        var alarmTerm = 0
//
//        var endHour = ""
//        var endMinute = ""
//
//        // setAlarmScreen에서 설정한 알람과 관련된 숫자들 모임
//        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
//        startHour = intent.getStringExtra("startH").toString()
//        startMinute = intent.getStringExtra("startM").toString()
//        alarmTerm = intent.getIntExtra("term" , 0)
//        endHour = intent.getStringExtra("endH").toString()
//        endMinute = intent.getStringExtra("endM").toString()
//
//        // 현재 시간
//        var formatter = DateTimeFormatter.ofPattern("HH:mm")
//        var currentTime = LocalTime.now().toString()
//        var currentTimeHourMinute = currentTime.format(formatter).split(":")
//        var currentHour = currentTimeHourMinute.get(0).trim()
//        var currentMinute = currentTimeHourMinute.get(1).trim()
//        Log.d("localTime : " , startHour)
//        Log.d("localTime : " , startMinute)
//        Log.d("localTime : " , "currentHour : $currentHour")
//        Log.d("localTime : " , endHour)
//        Log.d("localTime : " , endMinute)
//
//
//
//        // 알림 코드 시작
//        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationChannelId = "alarm_channel"
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(notificationChannelId, "Alarm Notifications", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(channel)
//        }
//        val cancelIntent = Intent(context , AlarmCancelReceiver ::class.java).apply {
//            // 알람 취소 액션 식별자 추가
//            action = "stopMusic"
//        }
//        val cancelPendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        val notificationBuilder = NotificationCompat.Builder(context, notificationChannelId)
//            .setSmallIcon(R.drawable.confirmbutton) // 알림 아이콘 설정
//            .setContentTitle("기상 시간 입니다.") // 알림 제목
////            .setContentText("Your alarm message: $message") // 알림 내용
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .addAction(R.drawable.add_icon, "알람 해제", cancelPendingIntent) // 알림에 알람 취소 버튼 추가
//            .setAutoCancel(true)
//
//        if(context != null && mediaPlayer == null) {
//            getMediaPlayer(context)
//        }
//
//
//        // 백그라운드에서 알람 관찰하기
//        val IoScope = CoroutineScope(Dispatchers.IO)
//        val musicScope = CoroutineScope(Dispatchers.Main)
//
//        when(intent.action) {
//            "startMusic" ->
//
//                IoScope.launch {
//
//                    while (isActive) {
//                        formatter = DateTimeFormatter.ofPattern("HH:mm")
//                        currentTime = LocalTime.now().toString()
//                        currentTimeHourMinute = currentTime.format(formatter).split(":")
//                        currentHour = currentTimeHourMinute.get(0).trim()
//                        currentMinute = currentTimeHourMinute.get(1).trim()
//
//                        if(startHour == currentHour && startMinute == currentMinute) {
//                            musicScope.launch {
//                                repeat(alarmTerm) {
//                                    MediaPlayerManager.startMusic()
//                                    delay(alarmTerm.toLong() * 1000 * 60)
//
//                                }
//
//                            }
//                            notificationManager.notify(1, notificationBuilder.build())
//                        }
//                    }
//
//                }
//
//
//
//
//
//        }
//        //
//
//    }
//
//
//}

//class AndroidAlarmScheduler @Inject constructor(
//    private val context: Context,
//) : AlarmScheduler {
//
//    private val alarmManager = context.getSystemService(AlarmManager::class.java)
//
//    override fun schedule(item: AlarmItem) {
//        val intent = Intent(context, AlarmReceiver::class.java).apply {
//            putExtra("startH", String.format("%02d", item.startH))
//            putExtra("startM", String.format("%02d", item.startM))
//            putExtra("term", item.term)
//            putExtra("endH", String.format("%02d", item.endH))
//            putExtra("endM", String.format("%02d", item.endM))
//
////            putExtra("startH", String.format("%02d", item.startH))
////            putExtra("startM", String.format("%02d", item.startM))
////            putExtra("term", item.term.toString())
////            putExtra("endH", String.format("%02d", item.endH))
////            putExtra("endM", String.format("%02d", item.endM))
//            putExtra("EXTRA_MESSAGE", item.message)
//            action = "startMusic"
//        }
//        alarmManager.setExactAndAllowWhileIdle(
//            AlarmManager.RTC_WAKEUP,
//            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
//            PendingIntent.getBroadcast(
//                context,
//                item.hashCode(),
//                intent,
//                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//            )
//        )
//    }
//
//    override fun cancel(item: AlarmItem) {
//        alarmManager.cancel(
//            PendingIntent.getBroadcast(
//                context,
//                item.hashCode(),
//                Intent(context, AlarmReceiver::class.java),
//                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//            )
//        )
//    }
//}

//class AlarmCancelReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
//        notificationManager?.cancel(1)
//
//        // 알람 취소 시 MainActivity 재시작
////        val restartIntent = Intent(context, MainActivity::class.java)
////
////            .apply {
////            // 새로운 작업으로 시작
////            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////        }
////        context?.startActivity(restartIntent)
//
//        if(intent?.action == "stopMusic") {
//            MediaPlayerManager.stopMusic()
////            context?.startActivity(restartIntent)
//
//        }
//    }
//}


class sibal {
}

//object MediaPlayerManager {
//    private var mediaPlayer: MediaPlayer? = null
//
//    fun getMediaPlayer(context: Context): MediaPlayer {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context, R.raw.sns)
//        }
//        return mediaPlayer!!
//    }
//
//    fun startMusic() {
//        mediaPlayer?.start()
//    }
//
//    fun stopMusic() {
//        mediaPlayer?.stop()
//        mediaPlayer?.release() // 사용 후 MediaPlayer 리소스 해제
//        mediaPlayer = null
//    }
//}