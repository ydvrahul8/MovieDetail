package com.example.moviesapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.repository.MovieDetailRepository

class MovieActivityViewModelFactory(private val repository: MovieDetailRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieActivityViewModel::class.java)){
            return MovieActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}