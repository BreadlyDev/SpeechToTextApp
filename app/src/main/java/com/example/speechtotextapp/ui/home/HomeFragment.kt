package com.example.speechtotextapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            txtKyrgyz.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_KyrgyzFragment)
            }
            txtSong.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_SongFragment)
            }
            txtMovie.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_MovieFragment)
            }
            txtBook.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_BookFragment)
            }
        }
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
//            songPage.setOnClickListener{
//                findNavController().navigate(R.id.action_HomeFragment_to_songsFragment)
//            }
        }

    }
}