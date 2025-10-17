package com.travelmarket.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.travelmarket.app.data.TravelViewModel
import com.travelmarket.app.navigation.Screen
import com.travelmarket.app.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "TravelMarket",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Search.route) }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = "Lugares") },
                    label = { Text("Lugares") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Places.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Event, contentDescription = "Eventos") },
                    label = { Text("Eventos") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Events.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DirectionsBus, contentDescription = "Servicios") },
                    label = { Text("Servicios") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Services.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route) }
                )
            }
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
                
                // Saludo de bienvenida
                Text(
                    text = "¡Bienvenido a Lima!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "Descubre los mejores lugares y eventos de los Juegos Panamericanos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Categorías principales
            item {
                Text(
                    text = "Categorías",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(getCategoryItems()) { category ->
                        CategoryCard(
                            title = category.title,
                            icon = category.icon,
                            color = category.color,
                            onClick = { navController.navigate(category.route) }
                        )
                    }
                }
            }
            
            // Lugares destacados
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lugares Destacados",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    
                    TextButton(
                        onClick = { navController.navigate(Screen.Places.route) }
                    ) {
                        Text("Ver todos")
                    }
                }
            }
            
            items(uiState.places.take(3)) { place ->
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
            
            // Eventos próximos
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Eventos Próximos",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    
                    TextButton(
                        onClick = { navController.navigate(Screen.Events.route) }
                    ) {
                        Text("Ver todos")
                    }
                }
            }
            
            items(uiState.events.take(2)) { event ->
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

@Composable
fun CategoryCard(
    title: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(120.dp)
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class CategoryItem(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val route: String
)

fun getCategoryItems(): List<CategoryItem> = listOf(
    CategoryItem("Lugares", Icons.Default.Place, Color(0xFF4CAF50), Screen.Places.route),
    CategoryItem("Eventos", Icons.Default.Event, Color(0xFF2196F3), Screen.Events.route),
    CategoryItem("Gastronomía", Icons.Default.Restaurant, Color(0xFFFF9800), Screen.Places.route),
    CategoryItem("Transporte", Icons.Default.DirectionsBus, Color(0xFF9C27B0), Screen.Services.route)
)
