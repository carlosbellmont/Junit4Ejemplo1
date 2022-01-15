package com.example.junit4ejjemplo

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    companion object {

        private lateinit var applicationName : String
        private lateinit var appContext: Context
        private var startingTime = 0L

        @BeforeClass
        @JvmStatic
        fun initVariables() {
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
            applicationName = appContext.resources.getString(R.string.app_name)
            startingTime = System.currentTimeMillis()

        }


        @AfterClass
        @JvmStatic
        fun showTotalTime() {
            println("The unit test were executed in ${System.currentTimeMillis() - startingTime} ")
        }
    }

    /**
     * Este test lanza tu aplicación y comprueba que el nombre del paquete sea igual al esperado
     */
    @Test
    fun checkPackageName() {
        assertEquals("com.example.junit4ejjemplo", appContext.packageName)
    }


    @Test
    fun checkApplicationNameContainsJunit() {
        assert(applicationName.contains("junit", true))
    }

    @Test
    fun checkApplicationNameContainsFour() {
        assert(applicationName.contains("4"))
    }

    @Test
    fun checkApplicationNameLengthIs14() {
        assert(applicationName.length == 14)
    }


    @Test
    fun checkValidEmailAndPass() {
        //onView(withId(R.id.etEmail)).perform(click())
        var firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())

        onView(withId(R.id.etEmail)).perform(typeText("my text"), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snack_logged)))
    }

}