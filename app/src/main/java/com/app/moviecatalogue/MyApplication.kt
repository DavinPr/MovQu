package com.app.moviecatalogue

import android.app.Application
import com.app.moviecatalogue.core.di.databaseModule
import com.app.moviecatalogue.core.di.networkModule
import com.app.moviecatalogue.core.di.repositoryModule
import com.app.moviecatalogue.presentation.di.useCaseModule
import com.app.moviecatalogue.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
                networkModule,
                repositoryModule,
                databaseModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

}