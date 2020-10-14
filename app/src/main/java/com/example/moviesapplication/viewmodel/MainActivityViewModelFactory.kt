package com.example.moviesapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.repository.MovieDetailRepository

class MainActivityViewModelFactory(private val sort_by: String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(sort_by) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}