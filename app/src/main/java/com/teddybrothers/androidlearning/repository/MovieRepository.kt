package com.teddybrothers.androidlearning.repository

import androidx.lifecycle.MutableLiveData
import com.teddybrothers.androidlearning.utils.RetrofitService
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.utils.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    var movieList = MutableLiveData<MovieListOutput>()


    fun getMovieList(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        val movieListCall =
            RetrofitService.instance().getMovieList(RetrofitService.API_KEY,sortBy =  sortBy, page = page)
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

}