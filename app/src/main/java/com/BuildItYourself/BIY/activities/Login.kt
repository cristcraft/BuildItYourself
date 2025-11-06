package com.BuildItYourself.BIY.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.BuildItYourself.BIY.R
import com.BuildItYourself.BIY.database.UsuarioDAO
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var usuarioDAO: UsuarioDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        //Inicializar el DAO
        usuarioDAO = UsuarioDAO(context = this)
        //Inicializar las vistas
        initViews()
        //setup de escuchas
        setupListener()



    }

    private fun setupListener() {
        btnLogin.setOnClickListener {
            iniciarSesion()
        }

    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }

    private fun iniciarSesion() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        //validaciones
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor complete los campos", Toast.LENGTH_SHORT).show()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Ingresa un correo que sea valido", Toast.LENGTH_SHORT).show()
            return
        }

        //Validar credenciales en la db
        if(usuarioDAO.validarLogin(email, password)){
            Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Datos Erroneos, por favor confirmar que la informacion ingresada sea correcta", Toast.LENGTH_SHORT).show()
            return
        }
    }
}


