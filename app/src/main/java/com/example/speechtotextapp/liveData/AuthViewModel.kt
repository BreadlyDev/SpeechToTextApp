package com.example.speechtotextapp.liveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel:ViewModel() {
    val token = MutableLiveData<String>()
}