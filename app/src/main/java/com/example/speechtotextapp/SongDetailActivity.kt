package com.example.speechtotextapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.speechtotextapp.api.RetrofitClient
import com.example.speechtotextapp.databinding.ActivitySongDetailBinding
import com.example.speechtotextapp.responses.AudioResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongDetailBinding
    private val apiInterface = RetrofitClient.apiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("productId", -1)
        Log.e("dfg", productId.toString())
        if (productId != -1) {

            CoroutineScope(Dispatchers.IO).launch {
                val product = RetrofitClient.apiInterface.getAudioById(productId)
                Log.e("dfg", product.toString())

                runOnUiThread{
                    if (product.isSuccessful) {
                        binding.apply {
                            txtTitle.text = product.body()?.title ?: "ошибка"
                            txtSubtitles.text = product.body()?.subtitles ?: "ошибка"
                        }
                    }
                }
            }
        }
    }


}
