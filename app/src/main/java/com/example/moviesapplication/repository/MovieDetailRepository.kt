package com.example.moviesapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapplication.db.MovieDetailDAO
import com.example.moviesapplication.model.MovieDetail
import com.example.moviesapplication.service.MovieDataService
import com.example.moviesapplication.service.RetrofitInstance
import com.example.moviesapplication.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository(private val dao: MovieDetailDAO,val id:String) {

    var movieDetail: LiveData<MovieDetail>? = null
    private var movies: MovieDetail? = null
    private val mutableLiveData: MutableLiveData<MovieDetail> = MutableLiveData()

    init {
        movieDetail = dao.getMovieDetail(id)

        if (movieDetail?.value == null) {
            fetchMovieDetail(id)
        }
    }

    suspend fun insert(movieDetail: MovieDetail): Long {
        return dao.insertMovieDetail(movieDetail)
    }

    private fun fetchMovieDetail(movieId: String){
        var movieDetail: MovieDetail? = null;
        val movieDataService: MovieDataService = RetrofitInstance.getMovieApi()
        val call: Call<MovieDetail> = movieDataService.getMovieDetails(movieId, API_KEY)

        call.enqueue(object : Callback<MovieDetail?> {

            override fun onResponse(call: Call<MovieDetail?>, response: Response<MovieDetail?>) {
                val movie: MovieDetail? = response.body()
                if (movie != null) {
                    movieDetail = movie
                    GlobalScope.launch(Dispatchers.Main) {
                       val data= insert(movieDetail!!)
                        Log.e("MovieDetailRepository", "onResponse: "+data )
                        mutableLiveData.value = this@MovieDetailRepository.movieDetail?.value
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetail?>, t: Throwable) {
            }
        })
    }

}
