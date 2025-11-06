package com.BuildItYourself.BIY.utils

import java.security.MessageDigest

//encriptar la contrasena del usuario
object PasswordHelper {
    fun hashPassword(password: String): String {
        //convertir string a bytes
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold(""){str, it -> str + "%02x".format(it)}
    }

    fun verificarPassword(password: String, passwordHashed: String): Boolean {
        return  hashPassword(password) == passwordHashed
    }
}