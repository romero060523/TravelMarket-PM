package com.tecsup.travelmarket.model

// Modelo de datos para representar un Evento
data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val date: String,
    val schedule: String,
    val category: String,
    val price: Double = 0.0,
    var isFavorite: Boolean = false
)


// Categorías disponibles para eventos
object EventCategory {
    const val SPORTS = "Deportes"
    const val CONCERT = "Conciertos"
    const val FESTIVAL = "Festivales"
    const val CONFERENCE = "Conferencias"
    const val EXHIBITION = "Exposiciones"
}