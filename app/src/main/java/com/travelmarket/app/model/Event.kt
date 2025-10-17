package com.travelmarket.app.model

import java.util.Date

data class Event(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val location: String,
    val date: Date,
    val time: String,
    val category: EventCategory,
    val price: String? = null,
    val isFavorite: Boolean = false,
    val organizer: String? = null,
    val capacity: Int? = null
)

enum class EventCategory {
    SPORTS,
    CULTURAL,
    MUSIC,
    FOOD,
    ART,
    EDUCATION,
    ENTERTAINMENT,
    CONFERENCE
}
