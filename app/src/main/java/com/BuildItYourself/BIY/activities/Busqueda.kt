package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.BuildItYourself.BIY.R

class Busqueda : AppCompatActivity() {
    //navbar
    private lateinit var btnInicio: ImageButton
    private lateinit var btnPerfil: ImageButton
    private lateinit var btnBusqueda: ImageButton
    private lateinit var btnCarrito: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_busqueda)
        initViews()
        //setup de escuchas
        setupListener()
    }

    private fun setupListener() {
        //navbar
        btnInicio.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }

        btnCarrito.setOnClickListener {
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        btnCarrito = findViewById(R.id.btnNavCarrito)
        btnInicio = findViewById(R.id.btnNavInicio)
        btnPerfil = findViewById(R.id.btnNavPerfil)
    }
}