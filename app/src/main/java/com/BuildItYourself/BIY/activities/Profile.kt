package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.BuildItYourself.BIY.R

class Profile : AppCompatActivity() {
    //navbar
    private lateinit var btnInicio: ImageButton
    private lateinit var btnPerfil: ImageButton
    private lateinit var btnBusqueda: ImageButton
    private lateinit var btnCarrito: ImageButton

    private lateinit var btnLoginActivity: Button
    private lateinit var btnRegistroActivity: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_porfile)

        initViews()
        //setup de escuchas
        setupListener()
    }

    private fun setupListener() {
        btnLoginActivity.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        btnRegistroActivity.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

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
    }

    private fun initViews() {
        btnLoginActivity = findViewById(R.id.btnLoginActivity)
        btnRegistroActivity = findViewById(R.id.btnRegistroActivity)
        //navbar
        btnInicio = findViewById(R.id.btnNavInicio)
        btnCarrito = findViewById(R.id.btnNavCarrito)
        btnBusqueda = findViewById(R.id.btnNavBusqueda)
        btnPerfil = findViewById(R.id.btnNavPerfil)
    }
}