package com.app.moviecatalogue.presentation.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.remote.RemoteDataSource
import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.data.remote.network.ApiService
import com.app.moviecatalogue.presentation.utils.dateFormat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    private lateinit var remote: RemoteDataSource
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        //Remote
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
    }

    @Test
    fun loadMovie() {
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_next)).perform(click())
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadTv() {
        onView(withId(R.id.rv_bottom_icon)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_next)).perform(click())
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(isRoot()).perform(waitFor(3000))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.date_movie)).check(matches(isDisplayed()))
        runBlocking {
            when (val detail = remote.getListMovieNowPlaying().first()) {
                is ApiResponse.Success -> {
                    onView(withId(R.id.title_movie)).check(matches(withText(detail.data[0].title)))
                    onView(withId(R.id.date_movie)).check(
                        matches(
                            withText(
                                detail.data[0].releaseDate.dateFormat(
                                    ApplicationProvider.getApplicationContext()
                                )
                            )
                        )
                    )
                }
                is ApiResponse.Error -> {
                    println("Error")
                }
                is ApiResponse.Empty -> {
                    println("Empty")
                }
            }
        }
    }

    @Test
    fun loadDetailTv() {
        onView(withId(R.id.rv_bottom_icon)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(isRoot()).perform(waitFor(3000))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.first_date_tv)).check(matches(isDisplayed()))
        runBlocking {
            when (val detail = remote.getListTvAiringToday().first()) {
                is ApiResponse.Success -> {
                    onView(withId(R.id.title_tv)).check(matches(withText(detail.data[0].name)))
                    onView(withId(R.id.first_date_tv)).check(
                        matches(
                            withText(
                                detail.data[0].firstAirDate.dateFormat(
                                    ApplicationProvider.getApplicationContext()
                                )
                            )
                        )
                    )
                }
                is ApiResponse.Error -> {
                    println("Error")
                }
                is ApiResponse.Empty -> {
                    println("Empty")
                }
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

    private fun withFirstIndex(matcher: Matcher<View?>): Matcher<View?> {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(0)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == 0
            }
        }
    }

}