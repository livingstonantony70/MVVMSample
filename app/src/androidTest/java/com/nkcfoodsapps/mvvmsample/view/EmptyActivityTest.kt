package com.nkcfoodsapps.mvvmsample.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nkcfoodsapps.mvvmsample.R
import com.nkcfoodsapps.mvvmsample.utils.getText
import com.nkcfoodsapps.mvvmsample.utils.waitForText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class EmptyActivityTest {


    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(EmptyActivity::class.java)


    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun test01_launchActivity_checkActivityIsDisplayed() {
        // Check that the activity is launched and displayed
        val myTextView = withId(R.id.myTextView)
        val myButton = withId(R.id.myButton)

        onView(myButton).check(
            matches(
                isDisplayed()
            )
        )
        onView(myTextView).check(
            matches(
                isDisplayed()
            )
        ) // Replace R.id.content_view with the ID of a view in your EmptyActivity

        onView(myTextView).check(matches(withText("--")))
        onView(myButton).perform(click())


        onView(withId(R.id.myTextView)).perform(waitForText("Loading..", 5000))
        onView(myTextView).perform(waitForText("Dummy API", 5000))
    }


    @After
    fun after() {
        Thread.sleep(3000L)
    }
}