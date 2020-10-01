package com.teddybrothers.androidlearning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teddybrothers.androidlearning.model.MovieDetail
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.repository.MovieRepository


class MovieViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var listOfMovies = MutableLiveData<MovieListOutput>()
    private var movieDetail = MutableLiveData<MovieDetail>()

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

    private fun loadMovieDetail(movieId : String): MutableLiveData<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }
}