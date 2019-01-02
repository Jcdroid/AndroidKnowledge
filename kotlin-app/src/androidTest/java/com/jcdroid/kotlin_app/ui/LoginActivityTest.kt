package com.jcdroid.kotlin_app.ui

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jcdroid.kotlin_app.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Jcdroid on 2018/11/16.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    val mTestRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun login() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_email)).perform(ViewActions.typeText("438339197@qq.com"))
        Espresso.onView(ViewMatchers.withId(R.id.tv_password)).perform(ViewActions.typeText("123456"))
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
    }
}