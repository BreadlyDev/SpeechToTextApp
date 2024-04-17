package com.example.speechtotextapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
//            btnSignUp.setOnClickListener {
//                findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
//            }
//            btnSignIn.setOnClickListener {
//
//            }
//            txtKyrgyz.setOnClickListener {
//                findNavController().navigate(R.id)
//            }
//            txtSong.setOnClickListener {
//                findNavController().navigate(R.id)
//            }
//            txtMovie.setOnClickListener {
//                findNavController().navigate(R.id)
//            }
//            txtBook.setOnClickListener {
//                findNavController().navigate(R.id)
//            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}