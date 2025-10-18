package com.travelmarket.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.travelmarket.app.ui.theme.TravelMarketTeal
import com.travelmarket.app.ui.theme.TravelMarketOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleHomeScreen(
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Logo circular como en Figma
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    Color.White,
                                    RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "Logo",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "TravelMarket",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Search.route) }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.White
                        )
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
                    onClick = { /*TODO*/ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = "Lugares") },
                    label = { Text("Lugares") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Places.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Eventos") },
                    label = { Text("Eventos") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Events.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Servicios") },
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Search Bar
            Spacer(modifier = Modifier.height(16.dp))
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.searchPlaces(it) },
                onSearch = { /* Handled by filtering */ },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Categories
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Categorías",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(getCategoryItems()) { category ->
                    CategoryCard(category = category) {
                        navController.navigate(category.route)
                    }
                }
            }

            // Featured Places
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Lugares Destacados",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            if (uiState.isLoading) {
                LoadingView(message = "Cargando lugares...")
            } else if (uiState.places.isEmpty()) {
                EmptyView(message = "No hay lugares disponibles")
            } else {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 400.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(uiState.places.take(3)) { place ->
                        PlaceCard(
                            place = place,
                            onItemClick = { placeId ->
                                navController.navigate(Screen.PlaceDetail.createRoute(placeId))
                            },
                            onFavoriteClick = { placeId ->
                                // TODO: Implementar favoritos
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CategoryCard(category: CategoryItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = category.color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.title,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}


