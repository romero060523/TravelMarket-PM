package com.tecsup.travelmarket.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.travelmarket.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TravelUiState(
    val places: List<Place> = emptyList(),
    val events: List<Event> = emptyList(),
    val services: List<Service> = emptyList(),
    val filteredPlaces: List<Place> = emptyList(),
    val filteredEvents: List<Event> = emptyList(),
    val filteredServices: List<Service> = emptyList(),
    val favoritePlaces: List<Place> = emptyList(),
    val favoriteEvents: List<Event> = emptyList(),
    val favoriteServices: List<Service> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: PlaceCategory? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

class TravelViewModel : ViewModel() {
    private val repository = Repository()
    
    private val _uiState = MutableStateFlow(TravelUiState())
    val uiState: StateFlow<TravelUiState> = _uiState.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    fun loadInitialData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    places = repository.getAllPlaces(),
                    events = repository.getAllEvents(),
                    services = repository.getAllServices(),
                    filteredPlaces = repository.getAllPlaces(),
                    filteredEvents = repository.getAllEvents(),
                    filteredServices = repository.getAllServices(),
                    favoritePlaces = repository.getFavoritePlaces(),
                    favoriteEvents = repository.getFavoriteEvents(),
                    favoriteServices = repository.getFavoriteServices(),
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
    
    fun searchPlaces(query: String) {
        viewModelScope.launch {
            val filteredPlaces = if (query.isBlank()) {
                repository.getAllPlaces()
            } else {
                repository.searchPlaces(query)
            }
            
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredPlaces = filteredPlaces
            )
        }
    }
    
    fun searchEvents(query: String) {
        viewModelScope.launch {
            val filteredEvents = if (query.isBlank()) {
                repository.getAllEvents()
            } else {
                repository.searchEvents(query)
            }
            
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredEvents = filteredEvents
            )
        }
    }
    
    fun searchServices(query: String) {
        viewModelScope.launch {
            val filteredServices = if (query.isBlank()) {
                repository.getAllServices()
            } else {
                repository.searchServices(query)
            }
            
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredServices = filteredServices
            )
        }
    }
    
    fun filterPlacesByCategory(category: PlaceCategory?) {
        viewModelScope.launch {
            val filteredPlaces = if (category == null) {
                repository.getAllPlaces()
            } else {
                repository.getPlacesByCategory(category)
            }
            
            _uiState.value = _uiState.value.copy(
                selectedCategory = category,
                filteredPlaces = filteredPlaces
            )
        }
    }
    
    fun filterEventsByCategory(category: EventCategory?) {
        viewModelScope.launch {
            val filteredEvents = if (category == null) {
                repository.getAllEvents()
            } else {
                repository.getEventsByCategory(category)
            }
            
            _uiState.value = _uiState.value.copy(
                filteredEvents = filteredEvents
            )
        }
    }
    
    fun filterServicesByCategory(category: ServiceCategory?) {
        viewModelScope.launch {
            val filteredServices = if (category == null) {
                repository.getAllServices()
            } else {
                repository.getServicesByCategory(category)
            }
            
            _uiState.value = _uiState.value.copy(
                filteredServices = filteredServices
            )
        }
    }
    
    fun togglePlaceFavorite(placeId: Int) {
        viewModelScope.launch {
            if (repository.isPlaceFavorite(placeId)) {
                repository.removePlaceFromFavorites(placeId)
            } else {
                repository.addPlaceToFavorites(placeId)
            }
            
            _uiState.value = _uiState.value.copy(
                favoritePlaces = repository.getFavoritePlaces()
            )
        }
    }
    
    fun toggleEventFavorite(eventId: Int) {
        viewModelScope.launch {
            if (repository.isEventFavorite(eventId)) {
                repository.removeEventFromFavorites(eventId)
            } else {
                repository.addEventToFavorites(eventId)
            }
            
            _uiState.value = _uiState.value.copy(
                favoriteEvents = repository.getFavoriteEvents()
            )
        }
    }
    
    fun toggleServiceFavorite(serviceId: Int) {
        viewModelScope.launch {
            if (repository.isServiceFavorite(serviceId)) {
                repository.removeServiceFromFavorites(serviceId)
            } else {
                repository.addServiceToFavorites(serviceId)
            }
            
            _uiState.value = _uiState.value.copy(
                favoriteServices = repository.getFavoriteServices()
            )
        }
    }
    
    fun getPlaceById(id: Int): Place? = repository.getPlaceById(id)
    fun getEventById(id: Int): Event? = repository.getEventById(id)
    fun getServiceById(id: Int): Service? = repository.getServiceById(id)
    
    fun isPlaceFavorite(id: Int): Boolean = repository.isPlaceFavorite(id)
    fun isEventFavorite(id: Int): Boolean = repository.isEventFavorite(id)
    fun isServiceFavorite(id: Int): Boolean = repository.isServiceFavorite(id)
}
