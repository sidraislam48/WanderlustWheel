package com.example.wanderlustwheel

import android.app.Application
import android.media.MediaPlayer

class App : Application() {

    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreate() {
        super.onCreate()
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
