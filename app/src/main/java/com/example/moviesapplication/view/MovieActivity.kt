package com.example.moviesapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesapplication.R
import com.example.moviesapplication.db.MovieDetailDatabase
import com.example.moviesapplication.model.MovieDetail
import com.example.moviesapplication.repository.MovieDetailRepository
import com.example.moviesapplication.utils.MOVIE_ID
import com.example.moviesapplication.viewmodel.MovieActivityViewModel
import com.example.moviesapplication.viewmodel.MovieActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {
    private var movie_id: Int? = null
    private var movieActivityViewModel: MovieActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)

        val intent: Intent = intent
        if (intent.hasExtra(MOVIE_ID)) {
            movie_id = getIntent().getIntExtra(MOVIE_ID, 0)
            val dao = MovieDetailDatabase.getInstance(applicationContext).movieDetailDAO
            val repository = MovieDetailRepository(dao,movie_id.toString())
            val factory = MovieActivityViewModelFactory(repository)
            movieActivityViewModel =
                ViewModelProvider(this, factory).get(MovieActivityViewModel::class.java)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            observerMovieDetail()
        }
    }

    private fun observerMovieDetail() {
        movieActivityViewModel?.movieDetail?.observe(this, Observer<MovieDetail>() {
            if (it != null)
                setDetails(it)
        })
    }

    private fun setDetails(movie: MovieDetail?) {

        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie?.backdrop_path)
            .into(imageView_bannerImage)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie?.poster_path)
            .into(imageView_posterImage)
        textView_title!!.text = movie?.original_title
        textView_rating!!.text = movie?.vote_average
        textView_description!!.text = movie?.overview
        setRuntime(movie)
        supportActionBar?.title = movie?.original_title
    }

    private fun setRuntime(movie: MovieDetail?) {
        val totalTime = movie?.runtime
        val hr = totalTime!! / 60
        val min = totalTime % 60
        textView_runtime.text = "( $hr hr $min min )"
    }
}