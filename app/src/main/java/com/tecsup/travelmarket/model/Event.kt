package com.tecsup.travelmarket.model

// Modelo de datos para representar un Evento
data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val date: String,
    val time: String,
    val schedule: String,
    val category: EventCategory,
    val price: Double = 0.0,
    val capacity: Int = 0,
    var isFavorite: Boolean = false
)

// Categorías disponibles para eventos
enum class EventCategory {
    SPORTS,
    CONCERT,
    FESTIVAL,
    CONFERENCE,
    EXHIBITION
}