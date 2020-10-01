package com.teddybrothers.androidlearning.utils

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.databinding.ListItemMovieGenreBinding
import com.teddybrothers.androidlearning.model.Genre
import java.util.*
import kotlin.collections.ArrayList

object BindingUtils {

    @JvmStatic
    @BindingAdapter("imagePath")
    fun ImageView.loadImage(imagePath: String?) {
        println(imagePath)
        imagePath.let {
            val imageUrl = "https://image.tmdb.org/t/p/w400".plus(imagePath)
            Glide.with(context)
                .load(imageUrl)
                .into(this)
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["genreIdList","movieGenreList"])
    fun FlexboxLayout.setMovieGenre(genreList: ArrayList<Int>?, movieGenreList: ArrayList<Genre>?) {
        this.removeAllViews()

        if (!movieGenreList.isNullOrEmpty()) {
            genreList?.forEach { genreId ->
                val genreName = movieGenreList.find { it.id == genreId }
                DataBindingUtil.bind<ListItemMovieGenreBinding>(
                    LayoutInflater.from(context).inflate(R.layout.list_item_movie_genre, null)
                )?.apply {
                    genre = genreName?.name
                    (root as TextView).setLayoutParamsMovieGenre()
                    this@setMovieGenre.addView(root)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("languageName")
    fun TextView.setLanguageName(languageCode: String?) {
        languageCode.let {
            val locale = Locale(it!!)
            this.text = locale.displayLanguage
        }

    }

    private fun TextView.setLayoutParamsMovieGenre() {
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 4.toDp(context), 4.toDp(context), 0)
            this@setLayoutParamsMovieGenre.layoutParams = this
        }
    }

    private fun Int.toDp(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

}