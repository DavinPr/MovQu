package com.app.moviecatalogue.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.data.local.LocalDataSource
import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.data.remote.RemoteDataSource
import com.app.moviecatalogue.core.data.remote.network.ApiService
import com.app.moviecatalogue.presentation.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockWebServer = MockWebServer()
    private lateinit var repository: FakeRepository
    private lateinit var local: LocalDataSource
    private lateinit var remote: RemoteDataSource

    @Before
    fun setup() {
        //remote
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()

        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://api.themoviedb.org/3/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        remote = RemoteDataSource(api)

        //local
        local = mock(LocalDataSource::class.java)

        repository = FakeRepository(remote, local)
    }

    @Test
    fun testGetListDiscoverMovie() {
        val discoverEntity: Flow<List<DiscoverMovieEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyDiscoverMovie())
            }
        `when`(local.getAllMovieDiscover()).thenReturn(discoverEntity)

        runBlockingTest {
            val data = repository.getListMovieDiscover().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyDiscoverMovie().size, data?.size)
        }
    }

    @Test
    fun getListMovieNowPlaying() {
        val nowPlaying: Flow<List<NowPlayingMovieEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyNowPlayingMovie())
            }
        `when`(local.getAllMovieNowPlaying()).thenReturn(nowPlaying)

        runBlockingTest {
            val data = repository.getListMovieNowPlaying().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyNowPlayingMovie().size, data?.size)
        }
    }

    @Test
    fun getListMovieUpcoming() {
        val upcoming: Flow<List<UpcomingMovieEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyUpcomingMovie())
            }
        `when`(local.getAllMovieUpcoming()).thenReturn(upcoming)

        runBlockingTest {
            val data = repository.getListMovieUpcoming().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyUpcomingMovie().size, data?.size)
        }
    }

    @Test
    fun getListTvDiscover() {
        val discoverTv: Flow<List<DiscoverTvShowEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyDiscoverTv())
            }
        `when`(local.getAllTvShowDiscover()).thenReturn(discoverTv)

        runBlockingTest {
            val data = repository.getListTvDiscover().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyDiscoverTv().size, data?.size)
        }
    }

    @Test
    fun getListTvAiringToday() {
        val airingToday: Flow<List<AiringTodayTvShowEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyAiringTodayTv())
            }
        `when`(local.getAllTvShowAiringToday()).thenReturn(airingToday)

        runBlockingTest {
            val data = repository.getListTvAiringToday().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyAiringTodayTv().size, data?.size)
        }
    }

    @Test
    fun getListTvOnTheAir() {
        val onTheAir: Flow<List<OnTheAirTvShowEntity>> =
            flow {
                emit(DataDummy.generateEntityDummyOnTheAirTv())
            }
        `when`(local.getAllTvShowOnTheAir()).thenReturn(onTheAir)

        runBlockingTest {
            val data = repository.getListTvOnTheAir().first().data
            assertNotNull(data)
            assertEquals(DataDummy.generateEntityDummyOnTheAirTv().size, data?.size)
        }
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            when (val detail = repository.getDetailMovie(399566.toString()).first()) {
                is Resource.Success -> {
                    assertNotNull(detail.data)
                    assertEquals(399566, detail.data?.id)
                    assertEquals("Godzilla vs. Kong", detail.data?.title)
                }
                is Resource.Error -> {
                    println("Test Failed")
                }
            }
        }
    }

    @Test
    fun getDetailTv() {
        runBlocking {
            when (val detail = repository.getDetailTv(1416.toString()).first()) {
                is Resource.Success -> {
                    assertNotNull(detail.data)
                    assertEquals(1416, detail.data?.id)
                    assertEquals("Grey's Anatomy", detail.data?.name)
                }
                is Resource.Error -> {
                    println("Test Failed")
                }
            }
        }
    }

    @Test
    fun `Test if fetch data error`() {
        runBlocking {
            when (val detail = repository.getDetailMovie(0.toString()).first()) {
                is Resource.Success -> {
                    println("Test Failed")
                }
                is Resource.Error -> {
                    assertNotNull(detail.message)
                }
            }
        }
    }

    @Test
    fun `Test if data empty`() {
        val discoverEntity: Flow<List<DiscoverMovieEntity>> =
            flow {
                emit(listOf<DiscoverMovieEntity>())
            }
        `when`(local.getAllMovieDiscover()).thenReturn(discoverEntity)

        runBlockingTest {
            val data = repository.getListMovieDiscover().first().data
            assertNotNull(data)
            assertEquals(0, data?.size)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}