package com.example.junit4ejjemplo

import com.example.junit4ejjemplo.ui.main.MainViewModel
import org.junit.AfterClass
import org.junit.Assert.assertFalse
import org.junit.BeforeClass
import org.junit.Test


class MainViewModelTest {

    private var viewModel = MainViewModel()


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
    fun `testing isValidEmail with correct values`(){
        val correctValues = arrayOf(
            "testerio@testeador.test",
            "Testerio1@testeador.test",
            "TESTERIO@testeador.test",
            "testerio.tester@testeador.test"
        )
        correctValues.forEach { email ->
            assert(viewModel.isEmailValid(email))
        }
    }

    @Test
    fun `testing isValidEmail with wrong values`(){
        val wrongValues = arrayListOf(
            "testerio@testeador,test", // no dot
            "testeriotesteador.test", // no @
            "testerio@tester@testeador.test" // Double @@
        )
        wrongValues.forEach { email ->
            assertFalse(viewModel.isEmailValid(email))
        }
    }


    @Test
    fun `testing isValidPassword with correct values`(){
        val correctValues = arrayOf(
            "0123456789aZ!",
        )
        correctValues.forEach { email ->
            assert(viewModel.isPasswordValid(email))
        }
    }

    @Test
    fun `testing isValidPassword with wrong values`(){
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
            assertFalse(viewModel.isPasswordValid(email))
        }
    }


    @Test
    fun comprobarContrasena(){
        val wrongValues = arrayListOf(
            "01234567890123456789aZ!", // too large
            "0aZ!", // too short
            "", // Empty
            "0123456789Z!", // no lower case
            "0123456789a!", // no upper case
            "0123456789aZ", // no symbol
            "aZ!aZ!aZ!", // no number
        )
        wrongValues.forEach { pass ->
            assertFalse(viewModel.isPasswordValid(pass))
        }
    }

}