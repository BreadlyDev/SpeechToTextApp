package com.example.speechtotextapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.api.RetrofitClient
import com.example.speechtotextapp.databinding.FragmentHomeBinding
import com.example.speechtotextapp.databinding.FragmentRegisterBinding
import com.example.speechtotextapp.liveData.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var authViewModel: AuthViewModel

    private fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiInterface.profile(authViewModel.token.toString())
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    binding.apply {
                        txtUsername.text = response.body()!!.username
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
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
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_HomeFragment_to_LoginFragment)
            }
        }
        getUser()
        super.onViewCreated(view, savedInstanceState)
    }
}