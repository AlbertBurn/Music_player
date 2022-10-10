package com.droiddev26.musicplayer

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var playButton: ImageView
    lateinit var playNextButton: ImageView
    lateinit var playPreviousButton: ImageView
    lateinit var volumeSeekBar: SeekBar
    lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        mediaPlayer = MediaPlayer.create(this, R.raw.still)
        playButton = findViewById(R.id.playBackground)
        playPreviousButton = findViewById(R.id.previousBackground)
        playNextButton = findViewById(R.id.nextBackground)
        volumeSeekBar = findViewById(R.id.volumeSeekBar)
        volumeSeekBar.setMax(maxVolume)
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Log.i("TAG", progress.toString())
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })
    }

    fun previousTrack(view: View) {}
    fun nextTrack(view: View) {}
    fun playAndPause(view: View) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause()
            //playStatus = true
            //playButton.setText("Play")
        } else {
            mediaPlayer.start()
            //playStatus = false
            //playButton.setText("Pause")
        }
    }
}
