package com.travelmarket.app.model

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val location: String,
    val category: PlaceCategory,
    val rating: Float,
    val isFavorite: Boolean = false,
    val openingHours: String? = null,
    val contact: String? = null
)

enum class PlaceCategory {
    TOURIST_ATTRACTION,
    RESTAURANT,
    HOTEL,
    SHOPPING,
    ENTERTAINMENT,
    CULTURAL,
    SPORTS,
    NATURE
}
