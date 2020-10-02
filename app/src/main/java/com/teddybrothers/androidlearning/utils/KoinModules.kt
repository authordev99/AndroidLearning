package com.teddybrothers.androidlearning.utils

import com.teddybrothers.androidlearning.repository.MovieRepository
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { MovieViewModel(get()) }
    factory { MovieRepository(get()) }
    single {
            Retrofit.Builder()
            .baseUrl(NetworkUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java)}
}