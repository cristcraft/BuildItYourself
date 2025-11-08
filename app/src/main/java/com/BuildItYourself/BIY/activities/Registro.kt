package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.BuildItYourself.BIY.R
import com.BuildItYourself.BIY.database.UsuarioDAO
import com.BuildItYourself.BIY.models.Usuario
import com.google.android.material.textfield.TextInputEditText

class Registro : AppCompatActivity() {
    //navbar
    private lateinit var btnInicio: ImageButton
    private lateinit var btnPerfil: ImageButton
    private lateinit var btnBusqueda: ImageButton
    private lateinit var btnCarrito: ImageButton

    private lateinit var etNombre: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etDireccion: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvIniciarSesion: TextView
    private val usuarioDAO = UsuarioDAO(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        //Inicializar el DAO
        val usuarioDAO = UsuarioDAO(context = this)

        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
        //Volver al login
        tvIniciarSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
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
        etNombre = findViewById(R.id.etNombre)
        etEmail = findViewById(R.id.etEmail)
        etDireccion = findViewById(R.id.etDireccion)
        etPassword = findViewById(R.id.etPassword)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion)
        //navbar
        btnInicio = findViewById(R.id.btnNavInicio)
        btnCarrito = findViewById(R.id.btnNavCarrito)
        btnBusqueda = findViewById(R.id.btnNavBusqueda)
        btnPerfil = findViewById(R.id.btnNavPerfil)
    }

    private fun Registro.registrarUsuario() {
        //obtener los valores de los campos
        val nombre = etNombre.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val direccion = etDireccion.text.toString().trim()
        val password = etPassword.text.toString().trim()

        //validar los datos
        when{
            nombre.isEmpty() || email.isEmpty() || direccion.isEmpty() || password.isEmpty() ->{
                Toast.makeText(this, "Por favor completar todos los campos", Toast.LENGTH_SHORT).show()
                return
            }

            //validacion de formato
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Ingresa un correo que sea valido", Toast.LENGTH_SHORT).show()
                return
            }
            //validacion de coincidencia

//            password != confirmarPassword -> {
//                Toast.makeText(this, "Las contrasenas no coinciden", Toast.LENGTH_SHORT).show()
//                return
//            }

            //validar longitud de contrasena
            password.length < 6 -> {
                Toast.makeText(this, "La contrasena debe de tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return
            }

            //verificar que el email no este registrado
            usuarioDAO.validarEmail(email)->{
                Toast.makeText(this, "El email ya esta registrado", Toast.LENGTH_SHORT).show()
                return
            }
        }


        //Si pasa todas las validaciones
        var usuario = Usuario(nombre = nombre, email = email, direccion = direccion, password = password)
        val registrado = usuarioDAO.registrarUsuario(usuario)
        if (registrado){
            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Hubo un error al registrar el usuario", Toast.LENGTH_SHORT).show()
        }
    }
}


