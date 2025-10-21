package com.tecsup.travelmarket.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.travelmarket.model.Event
import com.tecsup.travelmarket.model.Place
import com.tecsup.travelmarket.model.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel principal de TravelMarket
 * Gestiona el estado de la aplicación y coordina la lógica de negocio
 */
class TravelViewModel : ViewModel() {

    // Instancia única del repositorio
    private val repository = RepositoryProvider.repository

    // ==================== ESTADOS OBSERVABLES ====================

    // Lugares
    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places.asStateFlow()

    // Eventos
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    // Servicios
    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services.asStateFlow()

    // Resultados de búsqueda
    private val _searchResults = MutableStateFlow<SearchResult?>(null)
    val searchResults: StateFlow<SearchResult?> = _searchResults.asStateFlow()

    // Favoritos combinados
    private val _allFavorites = MutableStateFlow<List<Any>>(emptyList())
    val allFavorites: StateFlow<List<Any>> = _allFavorites.asStateFlow()

    // Favoritos por categoría
    private val _favoritePlaces = MutableStateFlow<List<Place>>(emptyList())
    val favoritePlaces: StateFlow<List<Place>> = _favoritePlaces.asStateFlow()

    private val _favoriteEvents = MutableStateFlow<List<Event>>(emptyList())
    val favoriteEvents: StateFlow<List<Event>> = _favoriteEvents.asStateFlow()

    private val _favoriteServices = MutableStateFlow<List<Service>>(emptyList())
    val favoriteServices: StateFlow<List<Service>> = _favoriteServices.asStateFlow()

    // Estado de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Mensajes de error
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // ==================== INICIALIZACIÓN ====================

    init {
        loadAllData()
    }

    // ==================== MÉTODOS PÚBLICOS ====================

    /**
     * Carga todos los datos iniciales
     */
    fun loadAllData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                loadPlaces()
                loadEvents()
                loadServices()
                loadAllFavorites()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar datos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Carga todos los lugares
     */
    fun loadPlaces() {
        viewModelScope.launch {
            _places.value = repository.getAllPlaces()
        }
    }

    /**
     * Carga todos los eventos
     */
    fun loadEvents() {
        viewModelScope.launch {
            _events.value = repository.getAllEvents()
        }
    }

    /**
     * Carga todos los servicios
     */
    fun loadServices() {
        viewModelScope.launch {
            _services.value = repository.getAllServices()
        }
    }

    /**
     * Obtiene servicios por categoría
     */
    fun getServicesByCategory(category: String): List<Service> {
        return repository.getServicesByCategory(category)
    }

    /**
     * Obtiene servicios por tipo
     */
    fun getServicesByType(type: String): List<Service> {
        return repository.getServicesByType(type)
    }

    /**
     * Busca elementos por query
     */
    fun search(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _searchResults.value = null
                return@launch
            }

            _searchResults.value = repository.searchItems(query)
        }
    }

    /**
     * Limpia los resultados de búsqueda
     */
    fun clearSearch() {
        _searchResults.value = null
    }

    // ==================== GESTIÓN DE FAVORITOS ====================

    /**
     * Alterna el estado de favorito de un lugar
     */
    fun toggleFavoritePlace(id: Int) {
        viewModelScope.launch {
            repository.toggleFavoritePlace(id)
            loadPlaces()
            loadAllFavorites()
            loadFavoritePlaces()
        }
    }

    /**
     * Alterna el estado de favorito de un evento
     */
    fun toggleFavoriteEvent(id: Int) {
        viewModelScope.launch {
            repository.toggleFavoriteEvent(id)
            loadEvents()
            loadAllFavorites()
            loadFavoriteEvents()
        }
    }

    /**
     * Alterna el estado de favorito de un servicio
     */
    fun toggleFavoriteService(id: Int) {
        viewModelScope.launch {
            repository.toggleFavoriteService(id)
            loadServices()
            loadAllFavorites()
            loadFavoriteServices()
        }
    }

    /**
     * Alterna favorito según tipo
     */
    fun toggleFavorite(id: Int, type: String) {
        when (type.lowercase()) {
            "place" -> toggleFavoritePlace(id)
            "event" -> toggleFavoriteEvent(id)
            "service" -> toggleFavoriteService(id)
        }
    }

    /**
     * Carga todos los favoritos
     */
    fun loadAllFavorites() {
        viewModelScope.launch {
            _allFavorites.value = repository.getAllFavorites()
            loadFavoritePlaces()
            loadFavoriteEvents()
            loadFavoriteServices()
        }
    }

    /**
     * Carga lugares favoritos
     */
    private fun loadFavoritePlaces() {
        _favoritePlaces.value = repository.getFavoritePlaces()
    }

    /**
     * Carga eventos favoritos
     */
    private fun loadFavoriteEvents() {
        _favoriteEvents.value = repository.getFavoriteEvents()
    }

    /**
     * Carga servicios favoritos
     */
    private fun loadFavoriteServices() {
        _favoriteServices.value = repository.getFavoriteServices()
    }

    /**
     * Verifica si un elemento está en favoritos
     */
    fun isFavorite(id: Int, type: String): Boolean {
        return repository.isFavorite(id, type)
    }

    /**
     * Obtiene el total de favoritos
     */
    fun getTotalFavoritesCount(): Int {
        return repository.getTotalFavoritesCount()
    }

    /**
     * Verifica si hay favoritos
     */
    fun hasFavorites(): Boolean {
        return repository.hasFavorites()
    }

    // ==================== BÚSQUEDA DE ELEMENTOS INDIVIDUALES ====================

    /**
     * Obtiene un lugar por ID
     */
    fun getPlaceById(id: Int): Place? {
        return repository.getPlaceById(id)
    }

    /**
     * Obtiene un evento por ID
     */
    fun getEventById(id: Int): Event? {
        return repository.getEventById(id)
    }

    /**
     * Obtiene un servicio por ID
     */
    fun getServiceById(id: Int): Service? {
        return _services.value.find { it.id == id }
    }

    /**
     * Limpia mensajes de error
     */
    fun clearError() {
        _errorMessage.value = null
    }
}
