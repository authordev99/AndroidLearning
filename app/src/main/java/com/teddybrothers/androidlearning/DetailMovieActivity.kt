package com.teddybrothers.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.teddybrothers.androidlearning.databinding.ActivityDetailMovieBinding
import com.teddybrothers.androidlearning.model.Genre
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    lateinit var movie: Movie
    lateinit var binding: ActivityDetailMovieBinding
    lateinit var movieViewModel: MovieViewModel
    private var movieGenreList = ArrayList<Genre>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        //retrieved value from list
        intent.extras?.apply {
            movie = Gson().fromJson(getString("movie"), Movie::class.java)

        }
        //setActionBar title
        title = movie.title

        //set movie binding
        binding.movie = movie

        //init view model
        init()
    }

    private fun init() {
        movieViewModel = ViewModelProviders.of(this).get<MovieViewModel>(
            MovieViewModel::class.java
        )
        //get genre List from repository
        getGenreList()
    }

    private fun getGenreList() {
        movieViewModel.getGenreMovieRepository()
            .observe(this, Observer { genreListResponse ->
                val genreListResults: List<Genre>? = genreListResponse.genreList
                println("genreList = $genreListResults")
                movieGenreList.addAll(genreListResults!!.toList())
                binding.movieGenreList = movieGenreList
            })
    }


}