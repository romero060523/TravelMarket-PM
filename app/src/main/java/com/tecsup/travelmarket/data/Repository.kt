package com.tecsup.travelmarket.data

import com.tecsup.travelmarket.model.*

class Repository {
    private val localData = LocalData
    
    // Places
    fun getAllPlaces(): List<Place> = localData.places
    
    fun getPlacesByCategory(category: PlaceCategory): List<Place> = 
        localData.places.filter { it.category == category }
    
    fun getPlaceById(id: Int): Place? = 
        localData.places.find { it.id == id }
    
    fun searchPlaces(query: String): List<Place> = 
        localData.places.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) ||
            it.location.contains(query, ignoreCase = true)
        }
    
    // Events
    fun getAllEvents(): List<Event> = localData.events
    
    fun getEventsByCategory(category: EventCategory): List<Event> = 
        localData.events.filter { it.category == category }
    
    fun getEventById(id: Int): Event? = 
        localData.events.find { it.id == id }
    
    fun searchEvents(query: String): List<Event> = 
        localData.events.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) ||
            it.location.contains(query, ignoreCase = true)
        }
    
    // Services
    fun getAllServices(): List<Service> = localData.services
    
    fun getServicesByCategory(category: ServiceCategory): List<Service> = 
        localData.services.filter { it.category == category }
    
    fun getServiceById(id: Int): Service? = 
        localData.services.find { it.id == id }
    
    fun searchServices(query: String): List<Service> = 
        localData.services.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true)
        }
    
    // Favorites
    private val favoritePlaces = mutableSetOf<Int>()
    private val favoriteEvents = mutableSetOf<Int>()
    private val favoriteServices = mutableSetOf<Int>()
    
    fun addPlaceToFavorites(placeId: Int) {
        favoritePlaces.add(placeId)
    }
    
    fun removePlaceFromFavorites(placeId: Int) {
        favoritePlaces.remove(placeId)
    }
    
    fun isPlaceFavorite(placeId: Int): Boolean = favoritePlaces.contains(placeId)
    
    fun getFavoritePlaces(): List<Place> = 
        localData.places.filter { favoritePlaces.contains(it.id) }
    
    fun addEventToFavorites(eventId: Int) {
        favoriteEvents.add(eventId)
    }
    
    fun removeEventFromFavorites(eventId: Int) {
        favoriteEvents.remove(eventId)
    }
    
    fun isEventFavorite(eventId: Int): Boolean = favoriteEvents.contains(eventId)
    
    fun getFavoriteEvents(): List<Event> = 
        localData.events.filter { favoriteEvents.contains(it.id) }
    
    fun addServiceToFavorites(serviceId: Int) {
        favoriteServices.add(serviceId)
    }
    
    fun removeServiceFromFavorites(serviceId: Int) {
        favoriteServices.remove(serviceId)
    }
    
    fun isServiceFavorite(serviceId: Int): Boolean = favoriteServices.contains(serviceId)
    
    fun getFavoriteServices(): List<Service> = 
        localData.services.filter { favoriteServices.contains(it.id) }
}
