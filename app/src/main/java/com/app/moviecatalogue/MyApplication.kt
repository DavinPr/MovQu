package com.app.moviecatalogue

import android.app.Application
import com.moviecatalogue.core.di.databaseModule
import com.moviecatalogue.core.di.networkModule
import com.moviecatalogue.core.di.repositoryModule
import com.app.moviecatalogue.presentation.di.useCaseModule
import com.app.moviecatalogue.presentation.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : Application() {

    @FlowPreview
    @ExperimentalCoroutinesApi
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

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

}