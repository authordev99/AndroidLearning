package com.teddybrothers.androidlearning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teddybrothers.androidlearning.model.MovieDetail
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.repository.MovieRepository
import io.reactivex.Observable


class MovieViewModel(val repository: MovieRepository) : ViewModel() {

    var listOfMovies = MutableLiveData<MovieListOutput>()
    var movieDetail = MutableLiveData<MovieDetail>()
    lateinit var movieDetailRx : Observable<MovieDetail>

    fun getMoviesRepository(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        listOfMovies = loadMoviesData(sortBy, page)
        return listOfMovies
    }

    private fun loadMoviesData(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        return repository.getMovies(sortBy, page)
    }

    fun getMovieDetailRepository(movieId: String): MutableLiveData<MovieDetail> {
        movieDetail = loadMovieDetail(movieId)
        return movieDetail
    }

    fun loadMovieDetail(movieId: String): MutableLiveData<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }

    fun getMovieDetailRepositoryRx(movieId: String): Observable<MovieDetail> {
        movieDetailRx = loadMovieDetailRx(movieId)
        return movieDetailRx
    }

    fun loadMovieDetailRx(movieId: String): Observable<MovieDetail> {
        return repository.getMovieDetailRx(movieId)
    }
}