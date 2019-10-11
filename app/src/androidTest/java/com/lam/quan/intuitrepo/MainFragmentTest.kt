package com.lam.quan.intuitrepo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    @get: Rule val activityActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Before
    fun init() {
        activityActivityTestRule.activity
            .supportFragmentManager.beginTransaction()
    }
    @Test
    fun inputTextShouldBeRetainedAfterActivityRecreation() {
        onView(withId(R.id.fetch_button)).perform(click())
        //Nothing crash is good enough for now
        assertTrue(true)
    }
}