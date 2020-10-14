package com.example.moviesapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapplication.R
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.utils.MOVIE_ID
import com.example.moviesapplication.view.adapter.MovieAdapter
import com.example.moviesapplication.viewmodel.MainActivityViewModel
import com.example.moviesapplication.viewmodel.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var movies: PagedList<Movie>? = null
    private var movieAdapter: MovieAdapter? = null
    private var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("TMDB Movies")

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.sort_by,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun discoverMovies(sort_by:String) {
        val factory = MainActivityViewModelFactory(sort_by)
        mainActivityViewModel =
            ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        mainActivityViewModel?.moviesPagedList?.observe(this,
            Observer<PagedList<Movie>> { moviesFromLiveData ->
                movies = moviesFromLiveData
                showOnRecyclerView()
            })
    }

    private fun showOnRecyclerView() {
        movieAdapter =
            MovieAdapter(this) { movie ->
                val intent = Intent(this, MovieActivity::class.java)
                intent.putExtra(MOVIE_ID, movie.id)
                startActivity(intent)
            }
        movieAdapter!!.submitList(movies)
        rvMovies?.layoutManager = GridLayoutManager(this, 2)
        rvMovies?.itemAnimator = DefaultItemAnimator()
        rvMovies?.adapter = movieAdapter
        movieAdapter!!.notifyDataSetChanged()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text = parent?.getItemAtPosition(position).toString()
        discoverMovies(text)
    }
}