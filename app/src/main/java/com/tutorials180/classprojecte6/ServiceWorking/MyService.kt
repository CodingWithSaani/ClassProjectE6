package com.tutorials180.classprojecte6.ServiceWorking

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import android.widget.Toast

class MyService : Service()
{

    private lateinit var mediaPlayer:MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this,
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))

        mediaPlayer.isLooping = true
        mediaPlayer.start()

        Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Stop", Toast.LENGTH_SHORT).show()

        mediaPlayer.stop()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}