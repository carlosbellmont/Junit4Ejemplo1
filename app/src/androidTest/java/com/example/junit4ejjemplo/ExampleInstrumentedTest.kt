package com.example.junit4ejjemplo

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    companion object {

        private lateinit var applicationName : String
        private lateinit var appContext: Context

        @BeforeClass
        @JvmStatic
        fun initVariables() {
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
            applicationName = appContext.resources.getString(R.string.app_name)
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

}