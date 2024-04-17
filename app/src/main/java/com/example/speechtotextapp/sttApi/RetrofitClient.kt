package com.example.speechtotextapp.sttApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    private const val BASE_URL = "https://asr.ulut.kg/api/receive_data"
    private const val BASE_URL = "http://0.0.0.0:8000"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: apiInterface by lazy {
        retrofit.create(apiInterface::class.java)
    }
}