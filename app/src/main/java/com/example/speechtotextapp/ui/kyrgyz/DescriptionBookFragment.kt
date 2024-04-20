package com.example.speechtotextapp.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentDescriptionBookBinding


class DescriptionBookFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieTitle = arguments?.getString("movieTitle")
        val movieSubtitles = arguments?.getString("movieSubtitles")
        val bookAuthor = arguments?.getString("movieVideo")

        binding.titleTextView.text = movieTitle
        binding.contentTextView.text = movieSubtitles


    }
}