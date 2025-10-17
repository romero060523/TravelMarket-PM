package com.travelmarket.app.model

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val category: ServiceCategory,
    val price: String? = null,
    val rating: Float,
    val isAvailable: Boolean = true,
    val contact: String? = null,
    val location: String? = null
)

enum class ServiceCategory {
    TRANSPORT,
    ACCOMMODATION,
    FOOD_DELIVERY,
    TOUR_GUIDE,
    PHOTOGRAPHY,
    TRANSLATION,
    MEDICAL,
    EMERGENCY
}
