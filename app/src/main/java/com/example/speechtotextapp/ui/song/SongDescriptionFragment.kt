package com.example.speechtotextapp.ui.song

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentSongDescriptionBinding

class SongDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentSongDescriptionBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private var isPlaying: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
        }

        val songTitle = arguments?.getString("songTitle")
        val songSubtitles = arguments?.getString("songSubtitles")

        binding.txtTitle.text = songTitle
        binding.txtSubtitles.text = songSubtitles

        val songAudio = arguments?.getString("songAudio")

        binding.idIBPlay.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                isPlaying = false
            } else {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                } else {
                    mediaPlayer.apply {
                        reset()
                        setDataSource(songAudio)
                        prepare()
                        start()
                        this@SongDescriptionFragment.isPlaying = true
                    }
                }
            }
        }

        seekBar = binding.seekBar
        mediaPlayer.setOnCompletionListener {
            isPlaying = false
            seekBar.progress = 0
        }

        val duration = mediaPlayer.duration
        seekBar.max = duration
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                val currentPosition = mediaPlayer.currentPosition
                seekBar.progress = currentPosition
                handler.postDelayed(this, 1000) // Обновляем каждую секунду
            }
        }, 0)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_SongDescriptionFragment_to_SongFragment)
        }
    }

    fun setAudio(audioPath: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(audioPath)
        mediaPlayer.prepare()
        mediaPlayer.start()
        isPlaying = true
    }
}
