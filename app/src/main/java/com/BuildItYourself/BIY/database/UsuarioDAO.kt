package com.BuildItYourself.BIY.database

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.BuildItYourself.BIY.models.Usuario
import com.BuildItYourself.BIY.utils.PasswordHelper

class UsuarioDAO(context: Context) {
    //Instancia del dataHelper para acceder a la DB
    private val dbHelper = DatabaseHelper(context)

    //funcion de registro
    fun registrarUsuario(usuario: Usuario): Boolean{
        //abrir el modo escritura de la base de datos
        val db = dbHelper.writableDatabase

        //encriptrar contrasena antes de que se guarde
        val passwordHased = PasswordHelper.hashPassword(usuario.password)

        //contenedor clave valor
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NOMBRE, usuario.nombre)
            put(DatabaseHelper.COLUMN_EMAIL, usuario.email)
            put(DatabaseHelper.COLUMN_DIRECCION, usuario.direccion)
            put(DatabaseHelper.COLUMN_PASSWORD, passwordHased)

        }

        //Insertar los valores en la tabla
        //Si retorna -1 si no se pudo insertar o retornar id en caso de ser exitoso
        val resultado = db.insert(DatabaseHelper.TABLE_USUARIOS, null, values)
        db.close()
        return resultado != -1L
    }

    //validar el login original
    fun validarLogin(email: String, password: String): Boolean {
        //obtener el usuario de la db por el email
        val usuario = obtenerUsuario(email)?:return false
        // verificar si la contrasena es correcta
        val passwordHased = PasswordHelper.hashPassword(password)
        if (passwordHased == usuario.password){
            return true
        }else{
            return false
        }
    }
    //validar el login prueba
//    fun validarLogin(email: String, password: String): Usuario? {
//        //obtener el usuario de la db por el email
//        val usuario = obtenerUsuario(email)
//        // verificar si la contrasena es correcta
//        val passwordHased = PasswordHelper.hashPassword(password)
//        return usuario
//    }
    //obtener usuario de la db
    fun obtenerUsuario(email: String): Usuario?{
        //abrir bd solo modo lectura
        var usuario: Usuario? = null
        val db = dbHelper.readableDatabase

        //Generar query
        val query = """ 
            SELECT * FROM ${DatabaseHelper.TABLE_USUARIOS} WHERE ${DatabaseHelper.COLUMN_EMAIL} = ?
            """
        val cursor = db.rawQuery(query,arrayOf(email))

        //recorrer los resultados de la consulta
        if (cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
            val direccion = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DIRECCION))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
            usuario = Usuario(id, nombre, email, direccion, password)
        }
        cursor.close()
        db.close()
        return usuario

    }
    //validar email
    fun validarEmail(email: String): Boolean{
        val db = dbHelper.readableDatabase
        //Generar query
        val query = """ 
            SELECT * FROM ${DatabaseHelper.TABLE_USUARIOS} WHERE ${DatabaseHelper.COLUMN_EMAIL} = ?
            """
        val cursor = db.rawQuery(query,arrayOf(email))
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
    //eliminar usuario
    fun eliminarUsuario(email: String): Boolean{
        val db = dbHelper.writableDatabase
        val resultado = db.delete(DatabaseHelper.TABLE_USUARIOS, "${ DatabaseHelper.COLUMN_EMAIL} = ?", arrayOf(email) )
        db.close()
        return resultado > 0
    }
    //actualizar contrasena
    fun actualizarPassword(email: String, nuevaPassword: String): Boolean{
        val db = dbHelper.writableDatabase
        val passwordHash = PasswordHelper.hashPassword(nuevaPassword)
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_PASSWORD, passwordHash)
        }
        val resulatdo = db.update(DatabaseHelper.TABLE_USUARIOS, values ,"${DatabaseHelper.COLUMN_EMAIL} = ?", arrayOf(email))
        db.close()
        return resulatdo > 0
    }
    //actualizar nombre
    fun actualizarNombre(email: String, nuevoNombre: String): Boolean{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NOMBRE, nuevoNombre)
        }
        val resulatdo = db.update(DatabaseHelper.TABLE_USUARIOS, values ,"${DatabaseHelper.COLUMN_EMAIL} = ?", arrayOf(email))
        db.close()
        return resulatdo > 0
    }
}