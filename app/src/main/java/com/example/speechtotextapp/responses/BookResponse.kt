package com.example.speechtotextapp.responses

data class BookResponse(
    val author: String,
    val content: String,
    val id: Int,
    val image: String,
    val title: String
)