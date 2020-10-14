package com.example.moviesapplication.repository

import androidx.paging.PageKeyedDataSource
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.MovieDBResponse
import com.example.moviesapplication.service.MovieDataService
import com.example.moviesapplication.service.RetrofitInstance
import com.example.moviesapplication.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieDataSource(private var movieDataService: MovieDataService,private val sort_by:String) :
    PageKeyedDataSource<Long, Movie?>() {
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie?>
    ) {
        movieDataService = RetrofitInstance.getMovieApi()
        val call =
            movieDataService.getPopularMoviesWithPaging(API_KEY, sort_by,1)
        call!!.enqueue(object : Callback<MovieDBResponse?> {
            override fun onResponse(
                call: Call<MovieDBResponse?>,
                response: Response<MovieDBResponse?>
            ) {
                val movieDBResponse = response.body()
                var movies: ArrayList<Movie?>? =
                    ArrayList()
                if (movieDBResponse?.Movies != null) {
                    movies =
                        movieDBResponse.Movies as ArrayList<Movie?>?
                    callback.onResult(movies!!, null, 2.toLong())
                }
            }

            override fun onFailure(
                call: Call<MovieDBResponse?>,
                t: Throwable
            ) {
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Movie?>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Movie?>
    ) {
        movieDataService = RetrofitInstance.getMovieApi()
        val call =
            movieDataService.getPopularMoviesWithPaging(API_KEY,sort_by, params.key)
        call!!.enqueue(object : Callback<MovieDBResponse?> {
            override fun onResponse(
                call: Call<MovieDBResponse?>,
                response: Response<MovieDBResponse?>
            ) {
                val movieDBResponse = response.body()
                var movies: ArrayList<Movie?>? =
                    ArrayList()
                if (movieDBResponse?.Movies != null) {
                    movies =
                        movieDBResponse.Movies as ArrayList<Movie?>?
                    callback.onResult(movies!!, params.key + 1)
                }
            }

            override fun onFailure(
                call: Call<MovieDBResponse?>,
                t: Throwable
            ) {
            }
        })
    }

}