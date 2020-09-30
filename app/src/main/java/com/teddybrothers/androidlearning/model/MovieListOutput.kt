package com.teddybrothers.androidlearning.model
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable

class MovieListOutput : Serializable {
    @SerializedName("page")
    val page : Int = 0
    @SerializedName("total_results")
    val  totalResults : Int = 0;
    @SerializedName("total_pages")
    val  totalPages : Int = 0;
    @SerializedName("results")
    val results : List<Movie>? = null
}