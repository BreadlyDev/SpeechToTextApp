package com.example.speechtotextapp.sttApi

import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.responses.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>
//    @POST("/login")
//    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>
//=======
//    suspend fun register(@Body registerRequest: RegisterRequest)
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @POST("logout")
    suspend fun logout(@Header("Authorization") bearer_token: String, @Body token: String)
}