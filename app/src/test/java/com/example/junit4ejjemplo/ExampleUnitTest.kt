package com.example.junit4ejjemplo

import com.example.junit4ejjemplo.ui.main.MainViewModel
import org.junit.Assert.*
import org.junit.Test


class ExampleUnitTest {

    private var viewModel = MainViewModel()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun isEmailValidWithValidEmail1(){
        assert(viewModel.isEmailValid("testerio@testeador.test"))
    }

    @Test
    fun isEmailValidInvalidNoDot(){
        assertFalse(viewModel.isEmailValid("testerio@testeador,test"))
    }

    @Test
    fun isEmailValidWithNoAt(){
        assertFalse(viewModel.isEmailValid("testeriotesteador.test"))
    }

    @Test
    fun isEmailValidWithDoubleDot(){
        assertTrue(viewModel.isEmailValid("testerio.tester@testeador.test"))
    }

    @Test
    fun isEmailValidWithDoubleAt(){
        assertFalse(viewModel.isEmailValid("testerio@tester@testeador.test"))
    }

}