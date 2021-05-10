package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide action bar
        supportActionBar?.hide()

        // Animate the image
        splash_image.animate().apply {
            duration = 2200 // 1 second
            rotationY(360f) // rotate 360 degrees on Y axis
        }.start()

        // Specify a Handler with a Looper to delay 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Open the MainActivity
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }, 2000) // 2 seconds delay
    }
}