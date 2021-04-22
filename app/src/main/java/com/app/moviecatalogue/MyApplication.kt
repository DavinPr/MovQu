package com.app.moviecatalogue

import android.app.Application
import com.app.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(applicationContext)
            modules(
                viewModelModule
            )
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

}