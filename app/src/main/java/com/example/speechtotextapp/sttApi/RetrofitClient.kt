package com.example.speechtotextapp.sttApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://https://asr.ulut.kg/api/receive_data"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val STTApiInterface: STTApiInterface by lazy {
        retrofit.create(STTApiInterface::class.java)
    }
}