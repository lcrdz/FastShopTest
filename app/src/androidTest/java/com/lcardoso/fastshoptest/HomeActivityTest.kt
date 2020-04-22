package com.lcardoso.fastshoptest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lcardoso.fastshoptest.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val rule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun whenActivityIsLaunched_shouldDisplay() {
        onView(withId(R.id.rvReleaseMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvGenres)).check(matches(isDisplayed()))
    }
}