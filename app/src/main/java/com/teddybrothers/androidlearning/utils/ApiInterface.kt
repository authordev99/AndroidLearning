package com.teddybrothers.androidlearning.utils

import com.teddybrothers.androidlearning.model.GenreListOutput
import com.teddybrothers.androidlearning.model.MovieDetail
import com.teddybrothers.androidlearning.model.MovieListOutput
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("3/discover/movie")
    fun getMovieList(@Query("api_key") apiKey: String,@Query("primary_release_date.lte") primaryReleaseDate : String = "2016-12-31",@Query("sort_by") sortBy: String, @Query("page") page: Int): Call<MovieListOutput>

    @GET("3/genre/movie/list")
    fun getMovieGenreList(@Query("api_key") apiKey: String): Call<GenreListOutput>

    @GET("3/movie")
    fun getMovieDetail(@Query("") movieId: String, @Query("api_key") apiKey: String): Call<MovieDetail>

}