package com.example.randomuserapiapp

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL


interface TextCallback {
    fun success(text: String)

    fun error()
}

class ApiSource(private val url: String, private val textCallback: TextCallback) {
    fun get() {
        Thread {
            try {
                val bufferedReader = BufferedReader(InputStreamReader(URL(url).openStream()))
                textCallback.success(bufferedReader.readText())
            } catch (e: Exception) {
                Log.d("error", e.toString())
                textCallback.error()
            }
        }.start()
    }
}