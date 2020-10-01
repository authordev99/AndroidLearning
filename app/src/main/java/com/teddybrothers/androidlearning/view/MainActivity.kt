package com.teddybrothers.androidlearning.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.adapter.RecyclerViewListener
import com.teddybrothers.androidlearning.adapter.RecyclerviewAdapter
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    RecyclerViewListener {

    companion object {
        const val RELEASE_DATE_DESC = "release_date.desc"
    }

    private lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private var listOfMovies = ArrayList<Movie>()
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setActionBar title
        title = "Movies App"

        init()
        getMovieList()
    }

    private fun init() {
        movieViewModel = ViewModelProviders.of(this).get<MovieViewModel>(
            MovieViewModel::class.java
        )
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter =
            RecyclerviewAdapter(this)
        recyclerview.adapter = recyclerviewAdapter

    }

    private fun getMovieList() {
        movieViewModel.getMoviesRepository(RELEASE_DATE_DESC, 1)
            .observe(this, Observer { movieListResponse ->
                val movieListResults: List<Movie>? = movieListResponse.results
                println("list = $movieListResults")
                listOfMovies.addAll(movieListResults!!.toList())
                recyclerviewAdapter.updateDataSet(listOfMovies)
            })
    }

    override fun onClickListener(item: Any, position: Int) {
        if (item is Movie) {
            startActivity(Intent(this,
                DetailMovieActivity::class.java).apply {
                putExtra("movie",Gson().toJson(item))
            })
        }
    }

}