package com.moviecatalogue.core.di

import androidx.room.Room
import com.moviecatalogue.core.BuildConfig
import com.moviecatalogue.core.data.AppRepository
import com.moviecatalogue.core.domain.usecase.repository.IAppRepository
import com.moviecatalogue.core.data.local.LocalDataSource
import com.moviecatalogue.core.data.local.room.FilmDatabase
import com.moviecatalogue.core.data.remote.RemoteDataSource
import com.moviecatalogue.core.data.remote.network.ApiService
import com.moviecatalogue.core.utils.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("film".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "film.db"
        ).openHelperFactory(factory)
            .fallbackToDestructiveMigration()
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