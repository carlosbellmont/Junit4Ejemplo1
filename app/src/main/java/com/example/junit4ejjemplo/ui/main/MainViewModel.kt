package com.example.junit4ejjemplo.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun isEmailValid(possibleEmail: String): Boolean {
        return possibleEmail.contains("@") && possibleEmail.contains(".") && (possibleEmail.count { it == '@' } == 1)
    }

}