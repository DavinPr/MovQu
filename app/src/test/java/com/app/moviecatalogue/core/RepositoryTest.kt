package com.app.moviecatalogue.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.data.local.LocalDataSource
import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.data.local.entity.favorite.FavoriteEntity
import com.app.moviecatalogue.core.data.remote.RemoteDataSource
import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.data.remote.response.MoviesItem
import com.app.moviecatalogue.core.data.remote.response.TvShowItem
import com.app.moviecatalogue.core.utils.AppExecutors
import com.app.moviecatalogue.core.utils.toEntity
import com.app.moviecatalogue.presentation.utils.DataDummy
import com.app.moviecatalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeRepository

    @Mock
    private lateinit var local: LocalDataSource

    @Mock
    private lateinit var remote: RemoteDataSource
    private lateinit var appExecutors: AppExecutors

    @Before
    fun setup() {
        appExecutors = AppExecutors()
        repository = FakeRepository(appExecutors, remote, local)
    }


    @Test
    fun testGetAllListMovie() {

        runBlockingTest {
            val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesItem>
            `when`(remote.getAllListDiscoverMovie()).thenReturn(dataSourceFactory)
            repository.getAllListMovieDiscover()

            val discover =
                Resource.Success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
            assertNotNull(discover.data)
            assertEquals(
                discover.data?.size?.toLong(),
                DataDummy.generateDummyMovie().size.toLong()
            )
        }
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
    fun testGetAllListTv() {

        runBlockingTest {
            val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowItem>
            `when`(remote.getAllListDiscoverTv()).thenReturn(dataSourceFactory)
            repository.getAllListTvDiscover()

            val discover =
                Resource.Success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
            assertNotNull(discover.data)
            assertEquals(discover.data?.size?.toLong(), DataDummy.generateDummyTv().size.toLong())
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
        val detailResponseDummy = DataDummy.generateDummyDetailMovieResponse()
        val detailResponse = MutableStateFlow(detailResponseDummy)
        runBlockingTest {
            `when`(remote.getDetailMovie(detailResponseDummy.id.toString())).thenReturn(
                detailResponse.map {
                    ApiResponse.Success(
                        it
                    )
                })
            repository.getDetailMovie(detailResponseDummy.id.toString()).collect {
                assertNotNull(it.data)
                assertEquals(detailResponseDummy.id, it.data?.id)
            }
            verify(remote, times(1)).getDetailMovie(detailResponseDummy.id.toString())
        }
    }


    @Test
    fun getDetailTv() {
        val detailResponseDummy = DataDummy.generateDummyDetailTvResponse()
        val detailResponse = MutableStateFlow(detailResponseDummy)
        runBlockingTest {
            `when`(remote.getDetailTv(detailResponseDummy.id.toString())).thenReturn(detailResponse.map {
                ApiResponse.Success(
                    it
                )
            })
            repository.getDetailTv(detailResponseDummy.id.toString()).collect {
                assertNotNull(it.data)
                assertEquals(detailResponseDummy.id, it.data?.id)
            }
            verify(remote, times(1)).getDetailTv(detailResponseDummy.id.toString())
        }
    }

    @Test
    fun testGetAllFavorite() {

        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(local.getAllFavorite()).thenReturn(dataSourceFactory)

        val discover =
            Resource.Success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        assertNotNull(discover.data)
        assertEquals(
            discover.data?.size?.toLong(),
            DataDummy.generateDummyMovie().size.toLong()
        )
    }

    @Test
    fun `Test if fetch data error`() {
        val detailResponseDummy = DataDummy.generateDummyDetailMovieResponse()
        val detailResponse = MutableStateFlow(detailResponseDummy)
        runBlockingTest {
            `when`(remote.getDetailMovie(detailResponseDummy.id.toString())).thenReturn(
                detailResponse.map {
                    ApiResponse.Error(
                        "Error"
                    )
                })
            repository.getDetailMovie(detailResponseDummy.id.toString()).collect {
                assertTrue(it is Resource.Error)
            }
            verify(remote, times(1)).getDetailMovie(detailResponseDummy.id.toString())
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

    @Test
    fun `Insert data favorite`() {
        runBlockingTest {
            val favorite = DataDummy.generateDummyFavorite().first()
            doNothing().`when`(local).insertFavorite(favorite.toEntity())
            repository.insertFavorite(favorite)

            verify(local, times(1)).insertFavorite(favorite.toEntity())
        }
    }

    @Test
    fun `Delete data favorite`() {
        runBlockingTest {
            val favorite = DataDummy.generateDummyFavorite().first()
            doNothing().`when`(local).deleteFavorite(favorite.toEntity())
            repository.deleteFavorite(favorite)

            verify(local, times(1)).deleteFavorite(favorite.toEntity())
        }
    }

    @Test
    fun `Check data is favorited`() {
        val isFavorited: Flow<Boolean> =
            flow {
                emit(true)
            }
        `when`(local.isFavorited("0")).thenReturn(isFavorited)

        runBlockingTest {
            val data = repository.isFavorited("0")
            assertEquals(true, data.first())
        }
    }
}