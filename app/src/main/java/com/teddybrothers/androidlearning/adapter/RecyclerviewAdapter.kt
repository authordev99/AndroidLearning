package com.teddybrothers.androidlearning.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.databinding.ListItemMovieBinding
import com.teddybrothers.androidlearning.model.Movie

class RecyclerviewAdapter(private val listener: RecyclerViewListener) : RecyclerView.Adapter<RecyclerviewAdapter.MainViewHolder>() {
    private var movieList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_movie,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gigData = this.movieList[position]
        holder.updateData(gigData)
    }

    override fun getItemCount() = movieList.size

    fun updateDataSet(movieList: List<Movie>) {
        if (movieList.isNotEmpty()) {
            this.movieList = movieList as ArrayList<Movie>
        }
        notifyDataSetChanged()
    }



    class MainViewHolder(val binding: ListItemMovieBinding, listener: RecyclerViewListener) : RecyclerView.ViewHolder(binding.root) {
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
}