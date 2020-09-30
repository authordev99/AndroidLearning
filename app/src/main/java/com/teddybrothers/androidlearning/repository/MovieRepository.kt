package com.teddybrothers.androidlearning.repository

import androidx.lifecycle.MutableLiveData
import com.teddybrothers.androidlearning.utils.RetrofitService
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    var apiInterface: ApiInterface? = null
    var movieList = MutableLiveData<MovieListOutput>()

    fun MovieRepository() {
        apiInterface =
            RetrofitService.apiInterface
    }

    fun getMovieList(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        val movieListCall =
            apiInterface?.getMovieList(RetrofitService.API_KEY, "release_date.desc", 1)
        movieListCall?.enqueue(object : Callback<MovieListOutput> {
            override fun onResponse(
                call: Call<MovieListOutput>,
                response: Response<MovieListOutput>
            ) {
                movieList.value = response.body()
            }

            override fun onFailure(call: Call<MovieListOutput>, t: Throwable) {
                movieList.postValue(null);
            }
        })

        return movieList
    }

}