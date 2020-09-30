package com.teddybrothers.androidlearning

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),RecyclerViewListener {

    private lateinit var recyclerviewAdapter: RecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter(this)
        recyclerview.adapter = recyclerviewAdapter

        val list = arrayListOf("Federico","Julian","Teddy")
        recyclerviewAdapter.updateDataSet(list)

    }

    override fun onClickListener(text: String, position: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}