package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.BuildItYourself.BIY.activities.MainActivity
import com.BuildItYourself.BIY.R

class Splash : AppCompatActivity() {
    private val splash_duration = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            //logica del redireccionamiento
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }, splash_duration)
    }
}