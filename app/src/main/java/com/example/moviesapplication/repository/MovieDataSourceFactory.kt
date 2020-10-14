package com.example.moviesapplication.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.service.MovieDataService

class MovieDataSourceFactory(
    private val movieDataService: MovieDataService,
    private val sort_by:String
) : DataSource.Factory<Long, Movie?>() {

    val mutableLiveData: MutableLiveData<MovieDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, Movie?> {
        val movieDataSource = MovieDataSource(movieDataService,sort_by)
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }

}