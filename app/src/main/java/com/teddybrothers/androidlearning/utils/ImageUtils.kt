package com.teddybrothers.androidlearning.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageUtils {

    @JvmStatic
    @BindingAdapter("imagePath")
    fun ImageView.loadImage(imagePath : String?) {
        imagePath.let {
            val imageUrl = "https://image.tmdb.org/t/p/w400".plus(imagePath)
            Glide.with(context)
                .load(imageUrl)
                .into(this)
        }

    }
}