package com.example.moviesapplication.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofitBuilder: Retrofit.Builder =
        Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit: Retrofit = retrofitBuilder.build()

    private val movieAPI: MovieDataService = retrofit.create(MovieDataService::class.java)

    fun getMovieApi(): MovieDataService {
        return movieAPI
    }
}