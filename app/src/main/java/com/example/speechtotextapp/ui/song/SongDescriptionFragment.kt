package com.example.speechtotextapp.ui.song

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentSongDescriptionBinding

class SongDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentSongDescriptionBinding
    private lateinit var mediaPlayer: MediaPlayer
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

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_SongDescriptionFragment_to_SongFragment)
        }
    }

    // Функция для установки аудиофайла для проигрывания извне, например, из адаптера RecyclerView
    fun setAudio(audioPath: String) {
        // Останавливаем предыдущее воспроизведение
        mediaPlayer.reset()
        // Устанавливаем новый источник аудио
        mediaPlayer.setDataSource(audioPath)
        // Подготавливаем mediaPlayer и начинаем воспроизведение
        mediaPlayer.prepare()
        mediaPlayer.start()
        // Обновляем состояние воспроизведения
        isPlaying = true
    }
}
