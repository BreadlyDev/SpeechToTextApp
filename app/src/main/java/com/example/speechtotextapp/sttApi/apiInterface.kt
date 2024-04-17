package com.example.speechtotextapp.sttApi

import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface apiInterface {
    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest)
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @POST("/logout")
    suspend fun logout(@Header("Authorization") bearer_token: String, @Body token: String)
}