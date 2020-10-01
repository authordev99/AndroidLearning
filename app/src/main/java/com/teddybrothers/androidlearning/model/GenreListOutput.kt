package com.teddybrothers.androidlearning.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GenreListOutput : Serializable {
    @SerializedName("genres")
    val genreList : List<Genre>? = null

}