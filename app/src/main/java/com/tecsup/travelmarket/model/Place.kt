package com.tecsup.travelmarket.model

//Modelo de datos para representar un Lugar turístico
data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val category: PlaceCategory,
    val schedule: String = "No especificado",
    val rating: Double = 0.0,
    var isFavorite: Boolean = false
)

//Categorías disponibles para lugares
enum class PlaceCategory {
    TOURIST_ATTRACTION,
    CULTURE,
    NATURE,
    HISTORICAL
}