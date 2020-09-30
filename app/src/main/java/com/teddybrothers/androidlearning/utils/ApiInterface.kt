package com.teddybrothers.androidlearning.utils

import com.teddybrothers.androidlearning.model.MovieListOutput
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    companion object {
        var BASE_URL = "https://http://api.themoviedb.org/3/discover/"
    }
    @GET("/movie")
    fun getMovieList(@Query("api_key") apiKey: String,@Query("sort_by") sortBy: String, @Query("page") page: Int): Call<MovieListOutput>

}