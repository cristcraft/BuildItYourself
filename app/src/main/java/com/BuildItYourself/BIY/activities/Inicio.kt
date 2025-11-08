package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.BuildItYourself.BIY.R

class Inicio : AppCompatActivity() {
    //navbar
    private lateinit var btnComprar: Button
    private lateinit var btnPerfil: ImageButton
    private lateinit var btnBusqueda: ImageButton
    private lateinit var btnCarrito: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        initViews()
        //setup de escuchas
        setupListener()

    }

    private fun setupListener() {

        btnCarrito.setOnClickListener {
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
            finish()
        }

        btnBusqueda.setOnClickListener {
            val intent = Intent(this, Busqueda::class.java)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }

        btnComprar.setOnClickListener {
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        btnCarrito = findViewById(R.id.btnNavCarrito)
        btnBusqueda = findViewById(R.id.btnNavBusqueda)
        btnPerfil = findViewById(R.id.btnNavPerfil)
        btnComprar = findViewById(R.id.btn_comprar)
    }
}