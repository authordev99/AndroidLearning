package com.teddybrothers.androidlearning.utils


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    const val BASE_URL = "https://api.themoviedb.org"
    const val API_KEY = "328c283cd27bd1877d9080ccb1604c91"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInterface : ApiInterface
        get() = retrofit.create(ApiInterface::class.java)
}