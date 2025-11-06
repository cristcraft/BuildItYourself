package com.BuildItYourself.BIY.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "UsuarioDB"
        private const val DATABASE_VERSION = 1

        const val TABLE_USUARIOS = "usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_DIRECCION =  "direccion"
        const val COLUMN_PASSWORD = "password"

        private const val CREATE_TABLE = """
            CREATE TABLE $TABLE_USUARIOS(
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_DIRECCION TEXT NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL
            )"""
    }

    //codigo para crear la bd por primera vez
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    //actualizar db si cambia la estructura
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

}