package com.tecsup.travelmarket.model

// Modelo para credenciales de usuario
data class UserCredentials(
    val email: String,
    val password: String
)

//Modelo para registro de usuario
data class RegisterData(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirmPassword: String
)