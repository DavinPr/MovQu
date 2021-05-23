package com.moviecatalogue.core.di

import androidx.room.Room
import com.moviecatalogue.core.BuildConfig
import com.moviecatalogue.core.data.AppRepository
import com.moviecatalogue.core.data.IAppRepository
import com.moviecatalogue.core.data.local.LocalDataSource
import com.moviecatalogue.core.data.local.room.FilmDatabase
import com.moviecatalogue.core.data.remote.RemoteDataSource
import com.moviecatalogue.core.data.remote.network.ApiService
import com.moviecatalogue.core.utils.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "film.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    factory { AppExecutors() }
    factory { CoroutineScope(Dispatchers.IO) }

    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
    single<IAppRepository> { AppRepository(get(), get(), get()) }
}