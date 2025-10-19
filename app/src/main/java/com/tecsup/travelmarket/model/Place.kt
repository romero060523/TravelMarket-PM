package com.tecsup.travelmarket.model

//Modelo de datos para representar un Lugar turístico
data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val category: String,
    val schedule: String = "No especificado",
    val rating: Double = 0.0,
    var isFavorite: Boolean = false
)


//Categorías disponibles para lugares
object PlaceCategory {
    const val TOURIST_ATTRACTION = "Lugares"
    const val CULTURE = "Cultura"
    const val NATURE = "Naturaleza"
    const val HISTORICAL = "Histórico"
}