package com.teddybrothers.androidlearning.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.gson.Gson
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.adapter.PaginationScrollListener
import com.teddybrothers.androidlearning.adapter.PaginationScrollListener.Companion.PAGE_START
import com.teddybrothers.androidlearning.adapter.RecyclerViewListener
import com.teddybrothers.androidlearning.adapter.RecyclerviewAdapter
import com.teddybrothers.androidlearning.model.Movie
import com.teddybrothers.androidlearning.model.MovieListOutput
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), RecyclerViewListener, OnRefreshListener {

    companion object {
        const val RELEASE_DATE_DESC = "release_date.desc"
    }

    private lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private var currentPage: Int = PAGE_START
    private var isLastPage = false
    private var isLoading = false
    private val movieViewModel by inject<MovieViewModel>()
    var itemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setActionBar title
        title = "Movies App"
        init()
        enableRefresh()
    }


    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Movies App"
        }

        swipeRefreshLayout.setOnRefreshListener(this)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter(this)

        recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = recyclerviewAdapter
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun loadMoreItems() {
                    isLoading = true
                    currentPage++
                    getMovieList()
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }
            })
        }
    }

    private fun getMovieList() {
        val call = movieViewModel.getMoviesRepository(RELEASE_DATE_DESC, currentPage)
        val observer = Observer<MovieListOutput> { movieListResponse ->
            if (movieListResponse != null) {
                val movieListResults = movieListResponse.results

                swipeRefreshLayout.disableRefresh()

                if (currentPage != PAGE_START) {
                    recyclerviewAdapter.removeLoading()
                }

                recyclerviewAdapter.addItems(movieListResults)

                if (currentPage < movieListResponse.totalPages) {
                    recyclerviewAdapter.addLoading()
                } else {
                    isLastPage = true
                }
                isLoading = false
            }

        }

        if (!call.hasActiveObservers()) {
            call.observe(this, observer)
        }
    }

    private fun SwipeRefreshLayout.disableRefresh() {
        postDelayed({ isRefreshing = false }, 1000)
    }

    private fun enableRefresh() {
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            getMovieList()
        }
    }

    override fun onClickListener(item: Any, position: Int) {
        if (item is Movie) {
            startActivity(Intent(
                this,
                DetailMovieActivity::class.java
            ).apply {
                putExtra("movie", Gson().toJson(item))
            })
        }
    }

    override fun onRefresh() {
        itemCount = 0
        currentPage = PAGE_START
        isLastPage = false
        recyclerviewAdapter.clear()
        enableRefresh()
    }

}