package com.example.speechtotextapp.sttApi

import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface apiInterface {
    @POST("/register")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>
    @POST("/logout")
    fun logout(@Header("Authorization") bearer_token: String, @Body token: String)
}