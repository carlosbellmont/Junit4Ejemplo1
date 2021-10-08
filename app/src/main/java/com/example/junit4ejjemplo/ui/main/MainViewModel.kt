package com.example.junit4ejjemplo.ui.main

import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class MainViewModel : ViewModel() {


    fun isEmailValid(possibleEmail: String): Boolean {
        return possibleEmail.contains("@") && possibleEmail.contains(".") && (possibleEmail.count { it == '@' } == 1)
    }

    fun isPasswordValid(possiblePassword: String): Boolean {
        val regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
        val pattern = Pattern.compile(regExp)
        val matcher = pattern.matcher(possiblePassword)
        return matcher.matches()
    }

}