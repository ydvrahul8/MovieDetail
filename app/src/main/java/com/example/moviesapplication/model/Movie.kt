package com.example.moviesapplication.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
class Movie : Parcelable {
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("video")
    @Expose
    val video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null

    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int> = ArrayList()

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null

    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null

    companion object {
        val CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return true
                }
            }
    }
}