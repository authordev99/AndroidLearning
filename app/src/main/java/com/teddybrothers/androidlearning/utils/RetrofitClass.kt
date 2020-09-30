package com.teddybrothers.androidlearning.utils


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    private var BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "328c283cd27bd1877d9080ccb1604c91"

    fun instance(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        println("baseUrl = $BASE_URL")

        return retrofit.create(ApiInterface::class.java)
    }
}