package com.teddybrothers.androidlearning.repository

import androidx.lifecycle.MutableLiveData
import com.teddybrothers.androidlearning.model.MovieDetail
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.utils.ApiInterface
import com.teddybrothers.androidlearning.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val apiInterface: ApiInterface) {
    var movieList = MutableLiveData<MovieListOutput>()
    var movieDetail = MutableLiveData<MovieDetail>()

    fun getMovies(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        val movieListCall = apiInterface.getMovieList(RetrofitService.API_KEY,sortBy =  sortBy, page = page)
        movieListCall.enqueue(object : Callback<MovieListOutput> {
            override fun onResponse(
                call: Call<MovieListOutput>,
                response: Response<MovieListOutput>
            ) {
                println("onResponse = $response")
                if (response.isSuccessful) {

                    movieList.value = response.body()
                }

            }

            override fun onFailure(call: Call<MovieListOutput>, t: Throwable) {
                println("onFailure = $t")
                movieList.postValue(null);
            }
        })

        return movieList
    }

    fun getMovieDetail(movieId : String): MutableLiveData<MovieDetail> {
        val movieDetailCall = apiInterface.getMovieDetail(movieId,RetrofitService.API_KEY)
        movieDetailCall.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(
                call: Call<MovieDetail>,
                response: Response<MovieDetail>
            ) {
                println("onResponse = $response")
                if (response.isSuccessful) {

                    movieDetail.value = response.body()
                }

            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                println("onFailure = $t")
                movieDetail.postValue(null);
            }
        })

        return movieDetail
    }

}