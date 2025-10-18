package com.travelmarket.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.travelmarket.app.data.TravelViewModel
import com.travelmarket.app.navigation.Screen
import com.travelmarket.app.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    
    LaunchedEffect(searchQuery) {
        viewModel.searchEvents(searchQuery)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eventos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Barra de búsqueda
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { viewModel.searchEvents(it) },
                placeholder = "Buscar eventos..."
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Lista de eventos
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                ErrorView(
                    message = uiState.error ?: "Error desconocido",
                    onRetry = { viewModel.loadInitialData() }
                )
            } else if (uiState.filteredEvents.isEmpty()) {
                EmptyView(
                    message = if (searchQuery.isBlank()) {
                        "No hay eventos disponibles"
                    } else {
                        "No se encontraron eventos para '$searchQuery'"
                    }
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.filteredEvents) { event ->
                        EventCard(
                            event = event,
                            onItemClick = { eventId ->
                                navController.navigate(Screen.EventDetail.createRoute(eventId))
                            },
                            onFavoriteClick = { eventId ->
                                viewModel.toggleEventFavorite(eventId)
                            }
                        )
                    }
                }
            }
        }
    }
}
