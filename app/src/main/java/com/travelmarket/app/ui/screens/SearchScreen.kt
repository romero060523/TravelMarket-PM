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
fun SearchScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }
    
    val tabs = listOf("Lugares", "Eventos", "Servicios")
    
    LaunchedEffect(searchQuery) {
        when (selectedTab) {
            0 -> viewModel.searchPlaces(searchQuery)
            1 -> viewModel.searchEvents(searchQuery)
            2 -> viewModel.searchServices(searchQuery)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar") },
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
                onSearch = { 
                    when (selectedTab) {
                        0 -> viewModel.searchPlaces(it)
                        1 -> viewModel.searchEvents(it)
                        2 -> viewModel.searchServices(it)
                    }
                },
                placeholder = "Buscar en TravelMarket..."
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Pestañas
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Contenido según la pestaña seleccionada
            when (selectedTab) {
                0 -> {
                    if (uiState.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else if (uiState.filteredPlaces.isEmpty()) {
                        EmptyView(
                            message = if (searchQuery.isBlank()) {
                                "Busca lugares interesantes"
                            } else {
                                "No se encontraron lugares para '$searchQuery'"
                            }
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.filteredPlaces) { place ->
                                PlaceCard(
                                    place = place,
                                    onItemClick = { placeId ->
                                        navController.navigate(Screen.PlaceDetail.createRoute(placeId))
                                    },
                                    onFavoriteClick = { placeId ->
                                        viewModel.togglePlaceFavorite(placeId)
                                    }
                                )
                            }
                        }
                    }
                }
                
                1 -> {
                    if (uiState.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else if (uiState.filteredEvents.isEmpty()) {
                        EmptyView(
                            message = if (searchQuery.isBlank()) {
                                "Busca eventos emocionantes"
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
                
                2 -> {
                    if (uiState.isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else if (uiState.filteredServices.isEmpty()) {
                        EmptyView(
                            message = if (searchQuery.isBlank()) {
                                "Busca servicios útiles"
                            } else {
                                "No se encontraron servicios para '$searchQuery'"
                            }
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.filteredServices) { service ->
                                ServiceCard(
                                    service = service,
                                    onItemClick = { serviceId ->
                                        navController.navigate(Screen.ServiceDetail.createRoute(serviceId))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
