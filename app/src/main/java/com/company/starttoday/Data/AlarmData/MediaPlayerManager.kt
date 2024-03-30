package com.company.starttoday.Data.AlarmData

import android.content.Context
import android.media.MediaPlayer
import com.company.starttoday.R

object MediaPlayerManager {
    private var mediaPlayer: MediaPlayer? = null

    fun getMediaPlayer(context: Context): MediaPlayer {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.sns)
        }
        return mediaPlayer!!
    }

    fun startMusic() {
        mediaPlayer?.start()
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release() // 사용 후 MediaPlayer 리소스 해제
        mediaPlayer = null
    }
}