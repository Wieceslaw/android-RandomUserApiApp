package com.example.randomuserapiapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val url = "https://randomuser.me/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val imageSource = ImageSource(object: ImageCallback {
            override fun success(bitmap: Bitmap) = runOnUiThread {
                imageView.setImageBitmap(bitmap)
            }

            override fun error() = runOnUiThread {
            }
        })
        val apiSource = ApiSource(url, object : TextCallback {
            override fun success(text: String) = runOnUiThread {
                val gson = Gson()
                val results = gson.fromJson(text, UserDTO::class.java).results[0]
                textView.text = "${results.name.first} ${results.name.last} located at ${results.location.country}, ${results.location.city}"
                imageSource.get(results.picture.large)
            }

            override fun error() = runOnUiThread {
                textView.text = "`error message`"
            }

        })
        button.setOnClickListener {
            apiSource.get()
        }
    }
}