package com.example.cuisine

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.presenter.CuisineDescriptionActivity
import com.example.cuisine.presenter.util.Constants
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CuisineDescriptionActivityTest {

    private lateinit var cuisine: Cuisine
    @get:Rule
    var rule: ActivityTestRule<CuisineDescriptionActivity> =
        ActivityTestRule(CuisineDescriptionActivity::class.java, true, false)

    @Test
    fun shouldBeAbleToShowDataFromCuisine() {
        cuisine = Cuisine(
            title = "Crispy Chicken and Rice	with Peas & Arugula Salad",
            description = "Crispy chicken skin, tender meat, and rich, tomatoey sauce form a winning trifecta of delicious in this one-pot braise. We spoon it over rice and peas to soak up every last drop of goodness, and serve a tangy arugula salad alongside for a vibrant boost of flavor and color. Dinner is served! Cook, relax, and enjoy!",
            photo = "https:////images.ctfassets.net/kk2bw5ojx476/5mFyTozvSoyE0Mqseoos86/fb88f4302cfd184492e548cde11a2555/SKU1479_Hero_077-71d8a07ff8e79abcb0e6c0ebf0f3b69c.jpg",
            calorie = 785.0,
            chef = "Jony Chives",
            tags = listOf("gluten free", "healthy")
        )
        launchActivity()
        onView(withId(R.id.titleText)).check(matches(withText(cuisine.title)))
        onView(withId(R.id.description)).check(matches(withText(cuisine.description)))
        onView(withId(R.id.chef)).check(matches(withText(cuisine.chef)))
        onView(withId(R.id.tagText)).check(matches(withText(cuisine.getStringFromTagsList())))
        onView(withId(R.id.titleText)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.description)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.chef)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tagText)).check(matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun shouldBeVisibilityGoneForChefWhenDataIsNotThere() {
        cuisine = Cuisine(
            title = "Crispy Chicken and Rice	with Peas & Arugula Salad",
            description = "Crispy chicken skin, tender meat, and rich, tomatoey sauce form a winning trifecta of delicious in this one-pot braise. We spoon it over rice and peas to soak up every last drop of goodness, and serve a tangy arugula salad alongside for a vibrant boost of flavor and color. Dinner is served! Cook, relax, and enjoy!",
            photo = "https:////images.ctfassets.net/kk2bw5ojx476/5mFyTozvSoyE0Mqseoos86/fb88f4302cfd184492e548cde11a2555/SKU1479_Hero_077-71d8a07ff8e79abcb0e6c0ebf0f3b69c.jpg",
            calorie = 785.0,
            tags = listOf("gluten free", "healthy"),
            chef = null
        )
        launchActivity()
        onView(withId(R.id.chef)).check(matches(not(ViewMatchers.isDisplayed())))

    }

    @Test
    fun shouldBeVisibilityGoneForTagsWhenDataIsNotThere() {
        cuisine = Cuisine(
            title = "Crispy Chicken and Rice	with Peas & Arugula Salad",
            description = "Crispy chicken skin, tender meat, and rich, tomatoey sauce form a winning trifecta of delicious in this one-pot braise. We spoon it over rice and peas to soak up every last drop of goodness, and serve a tangy arugula salad alongside for a vibrant boost of flavor and color. Dinner is served! Cook, relax, and enjoy!",
            photo = "https:////images.ctfassets.net/kk2bw5ojx476/5mFyTozvSoyE0Mqseoos86/fb88f4302cfd184492e548cde11a2555/SKU1479_Hero_077-71d8a07ff8e79abcb0e6c0ebf0f3b69c.jpg",
            calorie = 785.0,
            tags = null,
            chef = "Jony Chives"
        )
        launchActivity()
        onView(withId(R.id.tagText)).check(matches(not(ViewMatchers.isDisplayed())))

    }

    @Test
    fun shouldBeVisibilityGoneForTagsAndChefWhenDataIsNotThere() {
        cuisine = Cuisine(
            title = "Crispy Chicken and Rice	with Peas & Arugula Salad",
            description = "Crispy chicken skin, tender meat, and rich, tomatoey sauce form a winning trifecta of delicious in this one-pot braise. We spoon it over rice and peas to soak up every last drop of goodness, and serve a tangy arugula salad alongside for a vibrant boost of flavor and color. Dinner is served! Cook, relax, and enjoy!",
            photo = "https:////images.ctfassets.net/kk2bw5ojx476/5mFyTozvSoyE0Mqseoos86/fb88f4302cfd184492e548cde11a2555/SKU1479_Hero_077-71d8a07ff8e79abcb0e6c0ebf0f3b69c.jpg",
            calorie = 785.0,
            tags = null,
            chef = null
        )
        launchActivity()
        onView(withId(R.id.tagText)).check(matches(not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.chef)).check(matches(not(ViewMatchers.isDisplayed())))

    }

    private fun launchActivity() {
        val intent = Intent()
        intent.putExtra(Constants.DATA, cuisine)
        rule.launchActivity(intent)
    }


}