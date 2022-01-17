package com.example.junit4ejjemplo

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.junit4ejjemplo.ui.main.MainAndroidViewModel
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainAndroidViewModelTest {

    private var viewModel = MainAndroidViewModel(ApplicationProvider.getApplicationContext())


    companion object {
        private var startingTime = 0L

        @BeforeClass
        @JvmStatic
        fun checkInitialTime() {
            startingTime = System.currentTimeMillis()
        }

        @AfterClass
        @JvmStatic
        fun showTotalTime() {
            println("The unit test were executed in ${System.currentTimeMillis() - startingTime} ")
        }

    }

    @Test
    fun isEmailValid() {
        val correctValues = arrayOf(
            "testerio@testeador.test",
            "Testerio1@testeador.test",
            "TESTERIO@testeador.test",
            "testerio.tester@testeador.test"
        )
        correctValues.forEach { email ->
            assert(viewModel.isEmailValidUsingContext(email))
        }
        val wrongValues = arrayListOf(
            "testerio@testeador,test", // no dot
            "testeriotesteador.test", // no @
            "testerio@tester@testeador.test" // Double @@
        )
        wrongValues.forEach { email ->
            Assert.assertFalse(viewModel.isEmailValidUsingContext(email))
        }
    }

    @Test
    fun isPasswordValid() {
        val correctValues = arrayOf(
            "0123456789aZ!",
        )
        correctValues.forEach { email ->
            assert(viewModel.isPasswordValidUsingContext(email))
        }
        val wrongValues = arrayListOf(
            "01234567890123456789aZ!", // too large
            "0aZ!", // too short
            "", // Empty
            "0123456789Z!", // no lower case
            "0123456789a!", // no upper case
            "0123456789aZ", // no symbol
            "aZ!aZ!aZ!", // no number
        )
        wrongValues.forEach { email ->
            Assert.assertFalse(viewModel.isEmailValidUsingContext(email))
        }
    }
}