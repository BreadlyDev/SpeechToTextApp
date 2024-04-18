package com.example.speechtotextapp.ui.song

import MusicAdapter
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speechtotextapp.api.RetrofitClient
import com.example.speechtotextapp.databinding.FragmentSongBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SongFragment : Fragment() {

    private lateinit var binding: FragmentSongBinding
    private lateinit var adapter: MusicAdapter
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MusicAdapter()

        binding.musicRc.layoutManager = LinearLayoutManager(requireContext())
        binding.musicRc.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiInterface.getAllAudio()
            Log.e("SongFragment", "Failed to get audio list: ${response}")
            if (!response.isEmpty()) {
                withContext(Dispatchers.Main) {
                    binding.apply {
                        adapter.submitList(response)
                    }
                }
            } else {
                Log.e("SongFragment", "Failed to get audio list: ${response}")
            }
        }
    }
}

