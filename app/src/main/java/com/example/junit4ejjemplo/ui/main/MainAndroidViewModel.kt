package com.example.junit4ejjemplo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.junit4ejjemplo.R
import java.util.regex.Pattern

class MainAndroidViewModel(private val applicationContext: Application) : AndroidViewModel(applicationContext) {


    fun isEmailValidUsingContext(possibleEmail: String): Boolean {
        val at = applicationContext.getString(R.string.at)
        val dot = applicationContext.getString(R.string.dot)

        return possibleEmail.contains(dot) && (possibleEmail.count { it == at[0] } == 1)
    }

    fun isPasswordValidUsingContext(possiblePassword: String): Boolean {
        val regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
        val pattern = Pattern.compile(regExp)
        val matcher = pattern.matcher(possiblePassword)
        return matcher.matches()
    }

}