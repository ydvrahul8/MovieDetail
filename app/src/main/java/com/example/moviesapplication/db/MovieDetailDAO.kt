package com.example.moviesapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesapplication.model.MovieDetail

@Dao
interface MovieDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail): Long

    @Update
    suspend fun updateMovieDetail(movieDetail: MovieDetail): Int

    @Query("SELECT * FROM movie_detail_table WHERE id =:id")
    fun getMovieDetail(id: String): LiveData<MovieDetail>
}