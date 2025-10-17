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
fun ServicesScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    
    LaunchedEffect(searchQuery) {
        viewModel.searchServices(searchQuery)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Servicios") },
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
                onSearch = { viewModel.searchServices(it) },
                placeholder = "Buscar servicios..."
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Lista de servicios
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error != null) {
                ErrorView(
                    message = uiState.error,
                    onRetry = { viewModel.loadInitialData() }
                )
            } else if (uiState.filteredServices.isEmpty()) {
                EmptyView(
                    message = if (searchQuery.isBlank()) {
                        "No hay servicios disponibles"
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
