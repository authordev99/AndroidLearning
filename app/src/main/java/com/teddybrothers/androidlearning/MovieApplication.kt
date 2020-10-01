package com.teddybrothers.androidlearning

import android.app.Application
import com.teddybrothers.androidlearning.utils.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(listOf(
                viewModelModule
            ))
        }
    }
}