package com.travelmarket.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.travelmarket.app.data.TravelViewModel
import com.travelmarket.app.navigation.Screen
import com.travelmarket.app.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                
                // Información del usuario
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Avatar",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Usuario",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Text(
                            text = "usuario@travelmarket.com",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
            
            // Favoritos
            item {
                Text(
                    text = "Mis Favoritos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Lugares favoritos
            val favoritePlaces = uiState.places.filter { it.isFavorite }
            if (favoritePlaces.isNotEmpty()) {
                item {
                    Text(
                        text = "Lugares Favoritos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                items(favoritePlaces) { place ->
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
            
            // Eventos favoritos
            val favoriteEvents = uiState.events.filter { it.isFavorite }
            if (favoriteEvents.isNotEmpty()) {
                item {
                    Text(
                        text = "Eventos Favoritos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                items(favoriteEvents) { event ->
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
            
            if (favoritePlaces.isEmpty() && favoriteEvents.isEmpty()) {
                item {
                    EmptyView(
                        message = "No tienes favoritos aún. ¡Explora y agrega tus lugares y eventos favoritos!"
                    )
                }
            }
            
            // Configuración
            item {
                Text(
                    text = "Configuración",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        ListItem(
                            headlineContent = { Text("Notificaciones") },
                            leadingContent = {
                                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                            },
                            trailingContent = {
                                Switch(
                                    checked = true,
                                    onCheckedChange = { /* TODO: Implementar toggle */ }
                                )
                            }
                        )
                        
                        Divider()
                        
                        ListItem(
                            headlineContent = { Text("Modo Oscuro") },
                            leadingContent = {
                                Icon(Icons.Default.Settings, contentDescription = "Modo Oscuro")
                            },
                            trailingContent = {
                                Switch(
                                    checked = false,
                                    onCheckedChange = { /* TODO: Implementar toggle */ }
                                )
                            }
                        )
                        
                        Divider()
                        
                        ListItem(
                            headlineContent = { Text("Idioma") },
                            leadingContent = {
                                Icon(Icons.Default.Settings, contentDescription = "Idioma")
                            },
                            trailingContent = {
                                Text("Español")
                            }
                        )
                    }
                }
            }
        }
    }
}
