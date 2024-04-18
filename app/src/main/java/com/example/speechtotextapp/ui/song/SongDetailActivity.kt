package com.example.speechtotextapp.ui.song

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.speechtotextapp.R
import com.example.speechtotextapp.responses.AudioResponse

class SongDetailActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var audioFile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val titleTextView = findViewById<TextView>(R.id.title)
        val playButton = findViewById<Button>(R.id.playButton)

        val audioResponse: AudioResponse = intent.getSerializableExtra("audioResponse") as AudioResponse
        titleTextView.text = audioResponse.title
        audioFile = audioResponse.file

        mediaPlayer = MediaPlayer()

        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.apply {
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    setDataSource(audioFile)
                    prepare()
                    start()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }
}
