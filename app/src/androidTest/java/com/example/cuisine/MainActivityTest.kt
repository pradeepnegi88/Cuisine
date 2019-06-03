package com.example.cuisine

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.cuisine.presenter.MainActivity
import com.example.cuisine.presenter.adapter.CuisineAdapter
import com.example.cuisine.presenter.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    private lateinit var mainActivityIdlingResource: EspressoIdlingResource

    @Before
    fun setUp() {
        mainActivityIdlingResource = intentsRule.activity.getEspressoIdlingResourceForMainActivity()
        IdlingRegistry.getInstance().register(mainActivityIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mainActivityIdlingResource.idlingResource)
    }

    @Test
    fun validateRecyclerviewAndSearchIsDisplayed() {
        onView(withId(R.id.gridview)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))

    }

    @Test
    fun validateSearchText() {
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.gridview)).check(matches(isDisplayed()))
        onView(withId(R.id.gridview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CuisineAdapter.ViewHolder>(
                3,
                click()
            )
        )
        pressBack()
    }


}
