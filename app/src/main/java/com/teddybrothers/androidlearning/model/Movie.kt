package com.teddybrothers.androidlearning.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Movie  : Serializable{
    @SerializedName("popularity")
    val popularity : Double = 0.0
    @SerializedName("vote_count")
    val voteCount : Double = 0.0
    @SerializedName("video")
    val isVideo : Boolean = false
    @SerializedName("poster_path")
    val posterPath : String = ""
    @SerializedName("id")
    val id : Int = 0
    @SerializedName("adult")
    val isAdult : Boolean = false
    @SerializedName("backdrop_path")
    val backdropPath : String = ""
    @SerializedName("original_language")
    val originalLanguage : String = ""
    @SerializedName("original_title")
    val originalTitle : String = ""
    @SerializedName("genre_ids")
    val genreIds : ArrayList<Int>? = null
    @SerializedName("title")
    val title : String = ""
    @SerializedName("vote_average")
    val voteAverage : Double = 0.0
    @SerializedName("overview")
    val overview : String = ""
    @SerializedName("release_date")
    val releaseDate : String = ""
}