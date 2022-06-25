package com.example.randomuserapiapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.lang.Exception
import java.net.URL

class ImageSource(private val callback: ImageCallback) {
    fun get(url: String) {
        Thread {
            try {
            URL(url).openStream().use {
                callback.success(BitmapFactory.decodeStream(it))
            }
            } catch (e: Exception) {
                Log.d("imageError", e.toString())
                callback.error()
            }
        }.start()
    }
}

interface ImageCallback {
    fun success(bitmap: Bitmap)

    fun error()
}