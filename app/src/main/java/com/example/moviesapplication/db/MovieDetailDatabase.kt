package com.example.moviesapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapplication.model.MovieDetail

@Database(entities = [MovieDetail::class], version = 1)
abstract class MovieDetailDatabase : RoomDatabase() {
    abstract val movieDetailDAO: MovieDetailDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDetailDatabase? = null
        fun getInstance(context: Context): MovieDetailDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDetailDatabase::class.java,
                        "moviedetail_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}