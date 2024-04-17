package com.example.speechtotextapp.sttApi

import com.example.speechtotextapp.requests.LoginRequest
import com.example.speechtotextapp.requests.RegisterRequest
import com.example.speechtotextapp.responses.AudioResponse
import com.example.speechtotextapp.responses.AudioSubtitleResponse
import com.example.speechtotextapp.responses.AuthResponse
import com.example.speechtotextapp.responses.BookResponse
import com.example.speechtotextapp.responses.MovieResponse
import com.example.speechtotextapp.responses.ProfileResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @POST("logout")
    suspend fun logout(@Header("Authorization") bearer_token: String, @Body token: String)
    @GET("profile/")
    suspend fun profile(@Header("Authorization") bearer_token: String): Response<ProfileResponse>
    @GET("book/all/")
    suspend fun getAllBooks(): Response<List<BookResponse>>
    @GET("book/{id}/")
    suspend fun getBookById(@Path("id") id: Int): Response<BookResponse>
    @GET("movies/")
    suspend fun getAllMovies(): Response<List<MovieResponse>>
    @GET("movies/{id}/")
    suspend fun getMovieById(@Path("id") id: Int): Response<ResponseBody>
    @GET("movies/{id}/subtitles/")
    suspend fun getMovieSubtitlesById(@Path("id") id: Int): Response<MovieResponse>
    @GET("audio/")
    suspend fun getAllAudio(): Response<List<AudioResponse>>
    @GET("audio/{id}/")
    suspend fun getAudioById(@Path("id") id: Int): Response<ResponseBody>
    @GET("audio/{id}/subtitles/")
    suspend fun getAudioSubtitlesById(@Path("id") id: Int): Response<AudioSubtitleResponse>
}