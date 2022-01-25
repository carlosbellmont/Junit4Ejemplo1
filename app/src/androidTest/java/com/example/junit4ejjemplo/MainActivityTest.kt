package com.example.junit4ejjemplo

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.not
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>(intent)

    companion object {

        private lateinit var applicationName : String
        private lateinit var appContext: Context
        private var startingTime = 0L

        @BeforeClass
        @JvmStatic
        fun initVariables() {
            appContext = ApplicationProvider.getApplicationContext()//InstrumentationRegistry.getInstrumentation().targetContext
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
     * Este test lanza tu aplicación y comprueba que el nombre del paquete sea igual al esperado.
     * Puede ser muy interesante en caso de que tengáis Flavours en vuestra aplicación que cambien
     * el paquete.
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
    fun checkSnackBarWithValidEmailValidPass(){
        onView(withId(R.id.bLogIn)).check(matches(not(isEnabled())))

        onView(withId(R.id.etEmail)).perform(typeText("prueba@prueba.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("0123456789aZ!"), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).check(matches(isEnabled()))
        onView(withId(R.id.bLogIn)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snack_logged)))
    }

    @Test
    fun checkSnackBarWithInvalidEmailValidPass() {
        onView(withId(R.id.bLogIn)).check(matches(not(isEnabled())))

        onView(withId(R.id.etEmail)).perform(typeText("prueba@prueba,com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("0123456789aZ!"), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).check(matches(isEnabled()))
        onView(withId(R.id.bLogIn)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snack_wrong_email)))
    }

    @Test
    fun checkSnackBarWithValidEmailInvalidPass() {
        onView(withId(R.id.bLogIn)).check(matches(not(isEnabled())))

        onView(withId(R.id.etEmail)).perform(typeText("prueba@prueba.com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).check(matches(isEnabled()))
        onView(withId(R.id.bLogIn)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snack_wrong_pass)))
    }

    @Test
    fun checkSnackBarWithInvalidEmailInvalidPass() {
        onView(withId(R.id.bLogIn)).check(matches(not(isEnabled())))

        onView(withId(R.id.etEmail)).perform(typeText("prueba@prueba,com"), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).check(matches(isEnabled()))
        onView(withId(R.id.bLogIn)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.snack_wrong_email)))
    }

    @Test
    fun checkSnackBar() {
        checkSnackBarResult("prueba@prueba.com", "0123456789aZ!", appContext.getString(R.string.snack_logged))
        limpiarEditText()
        checkSnackBarResult("prueba@prueba,com", "1234", appContext.getString(R.string.snack_wrong_email))
        limpiarEditText()
        checkSnackBarResult("prueba@prueba.com", "1234", appContext.getString(R.string.snack_wrong_pass))
        limpiarEditText()
        checkSnackBarResult("prueba@prueba,com", "0123456789aZ!", appContext.getString(R.string.snack_wrong_email))
        limpiarEditText()
    }


    private fun checkSnackBarResult(email: String, password: String, expectedResult: String) {
        onView(withId(R.id.bLogIn)).check(matches(not(isEnabled())))

        onView(withId(R.id.etEmail)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.etPass)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.bLogIn)).check(matches(isEnabled()))
        onView(withId(R.id.bLogIn)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(expectedResult)))

    }

    private fun limpiarEditText() {
        onView(withId(R.id.etEmail)).perform(clearText())
        onView(withId(R.id.etPass)).perform(clearText())
    }
}