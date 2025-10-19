package com.tecsup.travelmarket.model

// Modelo de datos para un servicio
data class Service(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val type: String,
    val contact: String,
    val location: String,
    val category: ServiceCategory,
    val priceRange: String = "$$",
    val price: Double = 0.0,
    val schedule: String = "No especificado",
    var isFavorite: Boolean = false
)

// Categorías de servicios disponibles
enum class ServiceCategory {
    GASTRONOMY,
    TRANSPORT,
    LODGING,
    TOUR
}