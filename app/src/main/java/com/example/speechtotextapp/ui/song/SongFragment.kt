package com.example.speechtotextapp.ui.song

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.speechtotextapp.databinding.FragmentSongBinding


class SongFragment : Fragment() {

    private lateinit var binding: FragmentSongBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer()
        binding.apply {
            idIBPlay.setOnClickListener {
                var audioUrl = "http://192.168.88.77:8000/audio/2/"

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

                try {
                    mediaPlayer.setDataSource(audioUrl)

                    mediaPlayer.prepare()
                    mediaPlayer.start()

                } catch (e: Exception) {

                    e.printStackTrace()
                }
                Toast.makeText(context, "Audio started playing..", Toast.LENGTH_SHORT).show()

            }

            idIBPause.setOnClickListener{
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.release()
                    Toast.makeText(context, "Audio has been  paused..", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Audio not played..", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}

