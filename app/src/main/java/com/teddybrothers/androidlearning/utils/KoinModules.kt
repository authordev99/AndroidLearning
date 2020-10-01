package com.teddybrothers.androidlearning.utils

import com.teddybrothers.androidlearning.repository.MovieRepository
import com.teddybrothers.androidlearning.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    factory { MovieRepository(get()) }
    single { RetrofitService.instance() }
}