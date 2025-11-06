package com.BuildItYourself.BIY.models

data class Usuario(
    var Id: Int = 0,
    val nombre: String,
    val email: String,
    val direccion: String,
    val password: String
)
