package com.example.moviesapplication.service

import com.example.moviesapplication.model.MovieDBResponse
import com.example.moviesapplication.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataService {

    @GET("discover/movie")
    fun getPopularMoviesWithPaging(
        @Query("api_key") apiKey: String?,
        @Query("sort_by") sort_by: String?,
        @Query("page") page: Long
    ): Call<MovieDBResponse?>?

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path(value = "movie_id") movie_id: String,
        @Query("api_key") apiKey: String?
    ): Call<MovieDetail>
}