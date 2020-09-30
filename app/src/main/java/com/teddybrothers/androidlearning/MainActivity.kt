package com.teddybrothers.androidlearning

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecyclerViewListener {

    companion object {
        const val RELEASE_DATE_DESC = "release_date.desc"
    }

    private lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private var listOfMovies = ArrayList<Movie>()
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        getMovieList()


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

    private fun init() {
        movieViewModel = ViewModelProviders.of(this).get<MovieViewModel>(
            MovieViewModel::class.java
        )
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter(this)
        recyclerview.adapter = recyclerviewAdapter

    }

    override fun onClickListener(item: Any, position: Int) {
        if (item is Movie) {
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
    }

}