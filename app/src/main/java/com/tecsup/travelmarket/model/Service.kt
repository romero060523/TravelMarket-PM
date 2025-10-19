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
    val category: String,
    val priceRange: String = "$$",
    val schedule: String = "No especificado",
    var isFavorite: Boolean = false
)


// Tipos de servicios disponibles
object ServiceType {
    const val GASTRONOMY = "Gastronomía"
    const val TRANSPORT = "Transporte"
    const val LODGING = "Hospedaje"
    const val TOUR = "Tours"
}

// Categoria de gastronomia
object GastronomyCategory {
    const val RESTAURANT = "Restaurante"
    const val CAFE = "Café"
    const val BAR = "Bar"
    const val STREET_FOOD = "Comida Callejera"
}

// Categoria de transporte
object TransportCategory {
    const val TAXI = "Taxi"
    const val BUS = "Bus"
    const val TRAIN = "Tren"
    const val RENTAL = "Alquiler"
}