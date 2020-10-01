package com.teddybrothers.androidlearning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teddybrothers.androidlearning.model.GenreListOutput
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.repository.MovieRepository


class MovieViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var listOfMovies = MutableLiveData<MovieListOutput>()
    private var listofGenres = MutableLiveData<GenreListOutput>()

    fun getMoviesRepository(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        listOfMovies = loadMoviesData(sortBy, page)
        return listOfMovies
    }

    private fun loadMoviesData(sortBy: String, page: Int): MutableLiveData<MovieListOutput> {
        return repository.getMovies(sortBy, page)
    }

    fun getGenreMovieRepository(): MutableLiveData<GenreListOutput> {
        listofGenres = loadGenresData()
        return listofGenres
    }

    private fun loadGenresData(): MutableLiveData<GenreListOutput> {
        return repository.getGenres()
    }
}