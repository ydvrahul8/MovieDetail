package com.example.moviesapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.repository.MovieDataSource
import com.example.moviesapplication.repository.MovieDataSourceFactory
import com.example.moviesapplication.service.RetrofitInstance
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivityViewModel(val sort_by:String) : ViewModel() {
    var movieDataSourceLiveData: LiveData<MovieDataSource>

    private val executor: Executor

    public val moviesPagedList: LiveData<PagedList<Movie>>

    init {
        val movieDataService = RetrofitInstance.getMovieApi()
        val factory = MovieDataSourceFactory(movieDataService,sort_by)
        movieDataSourceLiveData = factory.mutableLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        moviesPagedList =
            LivePagedListBuilder<Long, Movie>(factory, config).setFetchExecutor(executor).build()
    }
}