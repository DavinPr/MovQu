package com.app.moviecatalogue.presentation.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.app.moviecatalogue.R
import com.app.moviecatalogue.presentation.utils.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadAllMovie() {
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.view_all))).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
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
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadAllTv() {
        onView(withId(R.id.rv_bottom_icon)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.viewpager_film)).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.view_all))).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun loadFavorite() {
        onView(withId(R.id.rv_bottom_icon)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_category)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        onView(withId(R.id.rv_category)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.activity_title)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_title)).check(matches(withText("Movie")))
        onView(withId(R.id.title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.date_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.btn_favorite)).perform(click())
    }

    @Test
    fun loadDetailTv() {
        onView(withId(R.id.rv_bottom_icon)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withFirstIndex(withId(R.id.rv_film))).check(matches(isDisplayed()))
        onView(withFirstIndex(withId(R.id.rv_film))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.activity_title)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_title)).check(matches(withText("Tv Show")))
        onView(withId(R.id.title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.first_date_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.btn_favorite)).perform(click())
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