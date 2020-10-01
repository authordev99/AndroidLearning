package com.teddybrothers.androidlearning.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.gson.Gson
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.adapter.RecyclerViewListener
import com.teddybrothers.androidlearning.adapter.RecyclerviewAdapter
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    RecyclerViewListener, OnRefreshListener {

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
        enableRefresh()
    }


    private fun init() {

        swipeRefreshLayout.setOnRefreshListener(this)
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
                listOfMovies.clear()
                listOfMovies.addAll(movieListResults!!.toList())
                recyclerviewAdapter.updateDataSet(listOfMovies)
                swipeRefreshLayout.disableRefresh()
            })
    }

    private fun SwipeRefreshLayout.disableRefresh(){
        postDelayed({ isRefreshing = false },1500)
    }

    private fun enableRefresh(){
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            getMovieList()}
    }

    override fun onClickListener(item: Any, position: Int) {
        if (item is Movie) {
            startActivity(Intent(this,
                DetailMovieActivity::class.java).apply {
                putExtra("movie",Gson().toJson(item))
            })
        }
    }

    override fun onRefresh() {
        enableRefresh()
    }

}