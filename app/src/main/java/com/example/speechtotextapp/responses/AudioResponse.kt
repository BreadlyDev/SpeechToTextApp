package com.example.speechtotextapp.responses

import android.media.MediaPlayer

data class AudioResponse(
    val audio_file: Any,
    val file: String,
    val id: Int,
    val subtitles: String,
    val title: String
)
