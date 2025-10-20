package com.tecsup.travelmarket.data

import com.tecsup.travelmarket.model.*

/**
 * Repositorio principal que gestiona todos los datos de la aplicación
 * Conecta con LocalData para proporcionar información a las pantallas
 */
class Repository {
    
    // Lista mutable para manejar favoritos
    private val favoritePlaces = mutableSetOf<Int>()
    private val favoriteEvents = mutableSetOf<Int>()
    private val favoriteServices = mutableSetOf<Int>()
    
    /**
     * Obtiene todos los lugares disponibles
     * @return Lista de todos los lugares
     */
    fun getAllPlaces(): List<Place> {
        return LocalData.places.map { place ->
            place.copy(isFavorite = favoritePlaces.contains(place.id))
        }
    }
    
    /**
     * Obtiene un lugar específico por su ID
     * @param id ID del lugar a buscar
     * @return El lugar encontrado o null si no existe
     */
    fun getPlaceById(id: Int): Place? {
        return LocalData.places.find { it.id == id }?.let { place ->
            place.copy(isFavorite = favoritePlaces.contains(place.id))
        }
    }
    
    /**
     * Obtiene todos los eventos disponibles
     * @return Lista de todos los eventos
     */
    fun getAllEvents(): List<Event> {
        return LocalData.events.map { event ->
            event.copy(isFavorite = favoriteEvents.contains(event.id))
        }
    }
    
    /**
     * Obtiene un evento específico por su ID
     * @param id ID del evento a buscar
     * @return El evento encontrado o null si no existe
     */
    fun getEventById(id: Int): Event? {
        return LocalData.events.find { it.id == id }?.let { event ->
            event.copy(isFavorite = favoriteEvents.contains(event.id))
        }
    }
    
    /**
     * Obtiene todos los servicios disponibles
     * @return Lista de todos los servicios
     */
    fun getAllServices(): List<Service> {
        return LocalData.services.map { service ->
            service.copy(isFavorite = favoriteServices.contains(service.id))
        }
    }
    
    /**
     * Obtiene servicios filtrados por categoría
     * @param category Categoría de servicio a filtrar
     * @return Lista de servicios que coinciden con la categoría
     */
    fun getServicesByCategory(category: String): List<Service> {
        return LocalData.services.filter { service ->
            service.category == category
        }.map { service ->
            service.copy(isFavorite = favoriteServices.contains(service.id))
        }
    }
    
    /**
     * Obtiene servicios filtrados por tipo
     * @param type Tipo de servicio a filtrar
     * @return Lista de servicios que coinciden con el tipo
     */
    fun getServicesByType(type: String): List<Service> {
        return LocalData.services.filter { service ->
            service.type == type
        }.map { service ->
            service.copy(isFavorite = favoriteServices.contains(service.id))
        }
    }
    
    /**
     * Busca elementos (lugares, eventos, servicios) por query
     * @param query Texto de búsqueda
     * @return Lista de resultados que coinciden con la búsqueda
     */
    fun searchItems(query: String): SearchResult {
        val searchQuery = query.lowercase().trim()
        
        if (searchQuery.isEmpty()) {
            return SearchResult(emptyList(), emptyList(), emptyList())
        }
        
        val matchingPlaces = LocalData.places.filter { place ->
            place.name.lowercase().contains(searchQuery) ||
            place.description.lowercase().contains(searchQuery) ||
            place.location.lowercase().contains(searchQuery) ||
            place.category.lowercase().contains(searchQuery)
        }.map { place ->
            place.copy(isFavorite = favoritePlaces.contains(place.id))
        }
        
        val matchingEvents = LocalData.events.filter { event ->
            event.name.lowercase().contains(searchQuery) ||
            event.description.lowercase().contains(searchQuery) ||
            event.location.lowercase().contains(searchQuery) ||
            event.category.lowercase().contains(searchQuery)
        }.map { event ->
            event.copy(isFavorite = favoriteEvents.contains(event.id))
        }
        
        val matchingServices = LocalData.services.filter { service ->
            service.name.lowercase().contains(searchQuery) ||
            service.description.lowercase().contains(searchQuery) ||
            service.location.lowercase().contains(searchQuery) ||
            service.type.lowercase().contains(searchQuery) ||
            service.category.lowercase().contains(searchQuery)
        }.map { service ->
            service.copy(isFavorite = favoriteServices.contains(service.id))
        }
        
        return SearchResult(matchingPlaces, matchingEvents, matchingServices)
    }
    
    /**
     * Alterna el estado de favorito de un lugar
     * @param id ID del lugar
     * @return true si se agregó a favoritos, false si se removió
     */
    fun toggleFavoritePlace(id: Int): Boolean {
        return if (favoritePlaces.contains(id)) {
            favoritePlaces.remove(id)
            false
        } else {
            favoritePlaces.add(id)
            true
        }
    }
    
    /**
     * Alterna el estado de favorito de un evento
     * @param id ID del evento
     * @return true si se agregó a favoritos, false si se removió
     */
    fun toggleFavoriteEvent(id: Int): Boolean {
        return if (favoriteEvents.contains(id)) {
            favoriteEvents.remove(id)
            false
        } else {
            favoriteEvents.add(id)
            true
        }
    }
    
    /**
     * Alterna el estado de favorito de un servicio
     * @param id ID del servicio
     * @return true si se agregó a favoritos, false si se removió
     */
    fun toggleFavoriteService(id: Int): Boolean {
        return if (favoriteServices.contains(id)) {
            favoriteServices.remove(id)
            false
        } else {
            favoriteServices.add(id)
            true
        }
    }
    
    /**
     * Obtiene todos los lugares marcados como favoritos
     * @return Lista de lugares favoritos
     */
    fun getFavoritePlaces(): List<Place> {
        return LocalData.places.filter { place ->
            favoritePlaces.contains(place.id)
        }.map { place ->
            place.copy(isFavorite = true)
        }
    }
    
    /**
     * Obtiene todos los eventos marcados como favoritos
     * @return Lista de eventos favoritos
     */
    fun getFavoriteEvents(): List<Event> {
        return LocalData.events.filter { event ->
            favoriteEvents.contains(event.id)
        }.map { event ->
            event.copy(isFavorite = true)
        }
    }
    
    /**
     * Obtiene todos los servicios marcados como favoritos
     * @return Lista de servicios favoritos
     */
    fun getFavoriteServices(): List<Service> {
        return LocalData.services.filter { service ->
            favoriteServices.contains(service.id)
        }.map { service ->
            service.copy(isFavorite = true)
        }
    }
    
    /**
     * Obtiene todos los favoritos (lugares, eventos y servicios)
     * @return Lista de todos los favoritos
     */
    fun getAllFavorites(): List<Any> {
        val favoritePlacesList = getFavoritePlaces()
        val favoriteEventsList = getFavoriteEvents()
        val favoriteServicesList = getFavoriteServices()
        
        return favoritePlacesList + favoriteEventsList + favoriteServicesList
    }
    
    /**
     * Verifica si un elemento está en favoritos
     * @param id ID del elemento
     * @param type Tipo del elemento ("place", "event", "service")
     * @return true si está en favoritos, false en caso contrario
     */
    fun isFavorite(id: Int, type: String): Boolean {
        return when (type.lowercase()) {
            "place" -> favoritePlaces.contains(id)
            "event" -> favoriteEvents.contains(id)
            "service" -> favoriteServices.contains(id)
            else -> false
        }
    }
    
    /**
     * Obtiene el usuario actual
     * @return Usuario actual
     */
    fun getCurrentUser(): User {
        return LocalData.currentUser
    }

    // ==================== ESTADÍSTICAS ====================

    /**
     * Obtener número total de favoritos
     */
    fun getTotalFavoritesCount(): Int {
        return getFavoritePlaces().size +
                getFavoriteEvents().size +
                getFavoriteServices().size
    }

    /**
     * Verificar si hay favoritos
     */
    fun hasFavorites(): Boolean {
        return getTotalFavoritesCount() > 0
    }
}

/**
 * Clase de datos para representar el resultado de una búsqueda
 */
data class SearchResult(
    val places: List<Place>,
    val events: List<Event>,
    val services: List<Service>
) {
    /**
     * Obtiene el total de resultados encontrados
     */
    val totalResults: Int
        get() = places.size + events.size + services.size
    
    /**
     * Verifica si hay resultados
     */
    val hasResults: Boolean
        get() = totalResults > 0
}
