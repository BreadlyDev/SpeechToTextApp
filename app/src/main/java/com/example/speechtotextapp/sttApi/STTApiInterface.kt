package com.example.speechtotextapp.sttApi

import retrofit2.http.POST

interface STTApiInterface {
    @POST("activity")
    fun getText() {

    }
}