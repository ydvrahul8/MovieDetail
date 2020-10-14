package com.example.moviesapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class MovieDBResponse:Parcelable {
    @SerializedName("page")
    @Expose
    val page: Int? = null

    @SerializedName("total_results")
    @Expose
    val totalMovies: Int? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null

    @SerializedName("results")
    @Expose
    val Movies: List<Movie>? = null
}