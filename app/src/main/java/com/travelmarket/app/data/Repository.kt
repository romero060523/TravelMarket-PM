package com.travelmarket.app.data

import com.travelmarket.app.model.*

class Repository {
    private val localData = LocalData
    
    fun getAllPlaces(): List<Place> = localData.getPlaces()
    
    fun getPlacesByCategory(category: PlaceCategory): List<Place> = 
        localData.getPlaces().filter { it.category == category }
    
    fun getPlaceById(id: String): Place? = 
        localData.getPlaces().find { it.id == id }
    
    fun getAllEvents(): List<Event> = localData.getEvents()
    
    fun getEventsByCategory(category: EventCategory): List<Event> = 
        localData.getEvents().filter { it.category == category }
    
    fun getEventById(id: String): Event? = 
        localData.getEvents().find { it.id == id }
    
    fun getAllServices(): List<Service> = localData.getServices()
    
    fun getServicesByCategory(category: ServiceCategory): List<Service> = 
        localData.getServices().filter { it.category == category }
    
    fun getServiceById(id: String): Service? = 
        localData.getServices().find { it.id == id }
    
    fun searchPlaces(query: String): List<Place> = 
        localData.getPlaces().filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) ||
            it.location.contains(query, ignoreCase = true)
        }
    
    fun searchEvents(query: String): List<Event> = 
        localData.getEvents().filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) ||
            it.location.contains(query, ignoreCase = true)
        }
    
    fun searchServices(query: String): List<Service> = 
        localData.getServices().filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true)
        }
}
