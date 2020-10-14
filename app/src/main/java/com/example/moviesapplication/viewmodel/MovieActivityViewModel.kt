package com.example.moviesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.model.MovieDetail
import com.example.moviesapplication.repository.MovieDetailRepository

class MovieActivityViewModel(val repository: MovieDetailRepository) :ViewModel(){

    val movieDetail = repository.movieDetail

}