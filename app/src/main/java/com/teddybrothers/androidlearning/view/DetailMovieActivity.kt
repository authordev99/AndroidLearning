package com.teddybrothers.androidlearning.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.databinding.ActivityDetailMovieBinding
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel

class DetailMovieActivity : AppCompatActivity() {

    lateinit var movie: Movie
    lateinit var binding: ActivityDetailMovieBinding
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_detail_movie
        )
        //retrieved value from list
        intent.extras?.apply {
            movie = Gson().fromJson(getString("movie"), Movie::class.java)

        }
        //setActionBar title
        title = movie.title

        //init view model
        init()
    }

    private fun init() {
        movieViewModel = ViewModelProviders.of(this).get<MovieViewModel>(
            MovieViewModel::class.java
        )
        getMovieDetail(movie.id.toString())
    }

    private fun getMovieDetail(movieId : String) {
        movieViewModel.getMovieDetailRepository(movieId)
            .observe(this, Observer { movieDetailResponse ->
                binding.movieDetail = movieDetailResponse
            })
    }


}