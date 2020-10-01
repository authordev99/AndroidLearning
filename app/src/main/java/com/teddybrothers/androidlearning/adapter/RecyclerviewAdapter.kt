package com.teddybrothers.androidlearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.databinding.ListItemLoadingBinding
import com.teddybrothers.androidlearning.databinding.ListItemMovieBinding
import com.teddybrothers.androidlearning.model.Movie


class RecyclerviewAdapter(private val listener: RecyclerViewListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movieList = ArrayList<Movie>()
    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_NORMAL) {
            return MainViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_movie,
                    parent,
                    false
                ),
                listener
            )
        } else {
            return ProgressViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_loading,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == movieList.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    fun addLoading() {
        isLoaderVisible = true
        notifyItemInserted(movieList.size)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position: Int = movieList.size - 1
        movieList.removeAt(position)
        notifyItemRemoved(position)

    }

    fun clear() {
        movieList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = this.movieList[position]
        if (holder is MainViewHolder) {
            holder.updateData(movie)
        }
    }

    override fun getItemCount() = movieList.size

    fun updateDataSet(movieList: List<Movie>) {
        if (movieList.isNotEmpty()) {
            this.movieList = movieList as ArrayList<Movie>
        }
        notifyDataSetChanged()
    }


    class MainViewHolder(val binding: ListItemMovieBinding, listener: RecyclerViewListener) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var movie: Movie

        init {
            itemView.setOnClickListener {
                listener.onClickListener(movie, layoutPosition)
            }
        }


        fun updateData(movie: Movie) {
            this.movie = movie
            binding.movie = movie
        }
    }

    class ProgressViewHolder(binding: ListItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)
}