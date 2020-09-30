package com.teddybrothers.androidlearning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.teddybrothers.androidlearning.databinding.ListItemMainBinding

class RecyclerviewAdapter(private val listener: RecyclerViewListener) : RecyclerView.Adapter<RecyclerviewAdapter.MainViewHolder>() {
    private var mainList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_main, parent, false), listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gigData = this.mainList[position]
        holder.updateData(gigData)
    }

    override fun getItemCount() = mainList.size

    fun updateDataSet(mainList: List<String>) {
        if (mainList.isNotEmpty()) {
            this.mainList = mainList as ArrayList<String>
        }
        notifyDataSetChanged()
    }



    class MainViewHolder(val binding: ListItemMainBinding, listener: RecyclerViewListener) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var data: String

        init {
            itemView.setOnClickListener {
                listener.onClickListener(data, layoutPosition)
            }
        }

        fun updateData(data: String) {
            this.data = data
            binding.textView.text = data
        }
    }
}