package com.app.moviecatalogue

import android.app.Application
import android.os.Build
import com.app.moviecatalogue.core.di.databaseModule
import com.app.moviecatalogue.core.di.networkModule
import com.app.moviecatalogue.core.di.repositoryModule
import com.app.moviecatalogue.presentation.di.useCaseModule
import com.app.moviecatalogue.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

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