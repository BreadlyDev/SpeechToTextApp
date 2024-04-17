package com.example.speechtotextapp.sttApi

import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface apiInterface {
    @POST("/register")
    fun register(@Body registerRequest: RegisterRequest)
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest)
    @POST("/logout")
    fun logout(@Header("Authorization") bearer_token: String, @Body token: String)
}