package com.teddybrothers.androidlearning.repository

import androidx.lifecycle.MutableLiveData
import com.teddybrothers.androidlearning.model.MovieDetail
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.utils.ApiInterface
import com.teddybrothers.androidlearning.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class MovieRepository(private val apiInterface: ApiInterface) {
    var movieList = MutableLiveData<MovieListOutput>()
    var movieDetail = MutableLiveData<MovieDetail>()
    var movieDetailRx = MutableLiveData<MovieDetail>()
    var compositeDisposable = CompositeDisposable()

    fun getMovies(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        val movieListCall = apiInterface.getMovieList(NetworkUtils.API_KEY,sortBy =  sortBy, page = page)
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
        val movieDetailCall = apiInterface.getMovieDetail(movieId,NetworkUtils.API_KEY)
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


    fun getMovieDetailRx(movieId : String) : Observable<MovieDetail> {
        return apiInterface
            .getMovieDetailRx(movieId,NetworkUtils.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}