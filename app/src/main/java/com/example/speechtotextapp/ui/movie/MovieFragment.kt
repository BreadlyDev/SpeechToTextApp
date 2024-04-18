package com.example.speechtotextapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.speechtotextapp.R
import com.example.speechtotextapp.adapter.MovieAdapter
import com.example.speechtotextapp.api.RetrofitClient
import com.example.speechtotextapp.databinding.FragmentMovieBinding
import com.example.speechtotextapp.help.SpaceItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private fun getAllMovies(adapter: MovieAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiInterface.getAllMovies()
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrEmpty()) {
                        val message = JSONObject(errorBody).getString("message")
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, "Возникла непредвиденная ошибка", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return@launch
                }

                val movie = response.body()
                if (movie != null) {
                    requireActivity().runOnUiThread {
                        adapter.updateList(response.body()!!)
                        Toast.makeText(context, "Вы успешно вошли в систему", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "Произошла Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        val adapter = MovieAdapter()
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_MovieFragment_to_HomeFragment)
        }
        binding.rvMovie.adapter = adapter
        binding.rvMovie.addItemDecoration(SpaceItemDecoration(20))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiInterface.getAllMovies()
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrEmpty()) {
                        val message = JSONObject(errorBody).getString("message")
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        requireActivity().runOnUiThread {
                            Toast.makeText(context, "Возникла непредвиденная ошибка", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return@launch
                }

                val movie = response.body()
                if (movie != null) {
                    requireActivity().runOnUiThread {
                        adapter.updateList(response.body()!!.toList())
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "Произошла Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}