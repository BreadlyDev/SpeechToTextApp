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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isPlaying: Boolean = false
        var mediaPlayer: MediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
        }

        val songTitle = arguments?.getString("songTitle")
        val songSubtitles = arguments?.getString("songSubtitles")
        val songAudio = arguments?.getString("songAudio")

        binding.txtTitle.text = songTitle
        binding.txtSubtitles.text = songSubtitles
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
                        isPlaying = true
                    }
                }
            }

            binding.btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_DescriptionFragment_to_MovieFragment)
            }
        }
    }
}