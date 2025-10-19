package com.tecsup.travelmarket.model

//Modelo de datos para representar un Usuario
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String = "",
    val profileImageRes: Int? = null,
    val favoritePlaces: MutableList<Int> = mutableListOf(),
    val favoriteEvents: MutableList<Int> = mutableListOf(),
    val favoriteServices: MutableList<Int> = mutableListOf()
)