package com.teddybrothers.androidlearning.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.databinding.ActivityMovieDetailsBinding
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.utils.BaseActivity
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class MovieDetailsActivity : BaseActivity() {

    companion object {
        const val PARAM_MOVIE = "movie"
    }

    lateinit var movie: Movie
    lateinit var binding: ActivityMovieDetailsBinding
    private val movieViewModel by inject<MovieViewModel>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_movie_details
        )

        //retrieved value from list
        intent.extras?.apply {
            movie = Gson().fromJson(getString(PARAM_MOVIE), Movie::class.java)
        }

        init()

    }

    private fun init() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = movie.title
        }

        bookNow.setOnClickListener {
            startActivity(Intent(this, BookNowActivity::class.java))
        }

        getMovieDetail(movie.id.toString())
    }

    private fun getMovieDetail(movieId: String) {
        val observableMovieDetail = movieViewModel.getMovieDetailRepositoryRx(movieId)
            .subscribe({ movieDetail ->
                println("Success = $movieDetail")
                binding.movieDetail = movieDetail
            }, { error ->
                println("Error = $error")
            }, { println("Complete") })

        compositeDisposable.add(observableMovieDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}