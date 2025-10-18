package com.travelmarket.app.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.travelmarket.app.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TravelViewModel : ViewModel() {
    private val repository = Repository()
    
    private val _uiState = MutableStateFlow(TravelUiState())
    val uiState: StateFlow<TravelUiState> = _uiState.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                places = repository.getAllPlaces(),
                events = repository.getAllEvents(),
                services = repository.getAllServices(),
                isLoading = false
            )
        }
    }
    
    fun searchPlaces(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredPlaces = if (query.isBlank()) {
                    repository.getAllPlaces()
                } else {
                    repository.searchPlaces(query)
                }
            )
        }
    }
    
    fun searchEvents(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredEvents = if (query.isBlank()) {
                    repository.getAllEvents()
                } else {
                    repository.searchEvents(query)
                }
            )
        }
    }
    
    fun searchServices(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                searchQuery = query,
                filteredServices = if (query.isBlank()) {
                    repository.getAllServices()
                } else {
                    repository.searchServices(query)
                }
            )
        }
    }
    
    fun togglePlaceFavorite(placeId: String) {
        viewModelScope.launch {
            val currentPlaces = _uiState.value.places.toMutableList()
            val index = currentPlaces.indexOfFirst { it.id == placeId }
            if (index != -1) {
                currentPlaces[index] = currentPlaces[index].copy(
                    isFavorite = !currentPlaces[index].isFavorite
                )
                _uiState.value = _uiState.value.copy(places = currentPlaces)
            }
        }
    }
    
    fun toggleEventFavorite(eventId: String) {
        viewModelScope.launch {
            val currentEvents = _uiState.value.events.toMutableList()
            val index = currentEvents.indexOfFirst { it.id == eventId }
            if (index != -1) {
                currentEvents[index] = currentEvents[index].copy(
                    isFavorite = !currentEvents[index].isFavorite
                )
                _uiState.value = _uiState.value.copy(events = currentEvents)
            }
        }
    }
    
    fun getPlaceById(id: String): Place? = repository.getPlaceById(id)
    fun getEventById(id: String): Event? = repository.getEventById(id)
    fun getServiceById(id: String): Service? = repository.getServiceById(id)
}

data class TravelUiState(
    val places: List<Place> = emptyList(),
    val events: List<Event> = emptyList(),
    val services: List<Service> = emptyList(),
    val filteredPlaces: List<Place> = emptyList(),
    val filteredEvents: List<Event> = emptyList(),
    val filteredServices: List<Service> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
)
