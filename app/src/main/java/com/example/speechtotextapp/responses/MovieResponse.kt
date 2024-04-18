package com.example.speechtotextapp.responses

data class MovieResponse(
    val audio_file: Any,
    val description: String,
    val file: String,
    val id: Int,
    val subtitles: String,
    val title: String,
    val image: String
)