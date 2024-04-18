package com.example.speechtotextapp.ui.movie

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentDescriptionMovieBinding


class MovieDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieTitle = arguments?.getString("movieTitle")
        val movieSubtitles = arguments?.getString("movieSubtitles")
        val movieVideo = arguments?.getString("movieVideo")

        binding.txtTitle.text = movieTitle
        binding.txtSubtitles.text = movieSubtitles
        binding.videoMovie.setVideoURI(Uri.parse(movieVideo))

        binding.videoMovie.setOnClickListener {
            binding.videoMovie.start()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_DescriptionFragment_to_MovieFragment)
        }
    }
}