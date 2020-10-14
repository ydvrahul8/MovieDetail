package com.example.moviesapplication.view.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapplication.R
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.view.adapter.MovieAdapter.MovieViewHolder

class MovieAdapter(private val context: Context, val listener: (Movie) -> Unit) :
    PagedListAdapter<Movie, MovieViewHolder?>(Movie.CALLBACK) {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: MovieViewHolder, position: Int) {
        val movie: Movie? = getItem(position)
        holder.onBind(movie)
    }

    inner class MovieViewHolder(@NonNull view: View) :
        RecyclerView.ViewHolder(view) {

        val moviePoster = view.findViewById<ImageView>(R.id.ivMovie)
        val movieName = view.findViewById<TextView>(R.id.tvTitle)
        val movieCard = view.findViewById<CardView>(R.id.cvMovie)

        fun onBind(movie: Movie?) {
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500" + movie?.posterPath)
                .placeholder(R.drawable.loading).into(moviePoster)
            movieName.text = movie?.title
            movieCard.setOnClickListener { listener(movie!!) }
        }

    }

}