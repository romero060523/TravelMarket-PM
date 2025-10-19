package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.Repository
import com.tecsup.travelmarket.data.SearchResult
import com.tecsup.travelmarket.model.*
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard
import com.tecsup.travelmarket.ui.components.SearchBar
import com.tecsup.travelmarket.ui.theme.*

data class Category(
    val name: String,
    val icon: ImageVector,
    val color: Color,
    val route: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var showSearchResults by remember { mutableStateOf(false) }
    val repository = remember { Repository() }
    
    // Obtener datos del repositorio
    val places = repository.getAllPlaces()
    val events = repository.getAllEvents()
    val services = repository.getAllServices()
    
    // Búsqueda reactiva
    val searchResults by remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isNotBlank()) {
                repository.searchItems(searchQuery)
            } else {
                SearchResult(emptyList(), emptyList(), emptyList())
            }
        }
    }
    
    // Mostrar resultados cuando hay búsqueda
    LaunchedEffect(searchQuery) {
        showSearchResults = searchQuery.isNotBlank() && searchResults.hasResults
    }

    val categories = listOf(
        Category("Lugares", Icons.Default.Place, TurquoiseCategory, Screen.Places.route),
        Category("Eventos", Icons.Default.Event, OrangeCategory, Screen.Events.route),
        Category("Gastronomía", Icons.Default.Restaurant, TurquoiseCategory, Screen.Gastronomy.route),
        Category("Transporte", Icons.Default.DirectionsBus, OrangeCategory, Screen.Transport.route)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {
        // Header con logo y perfil
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = TurquoisePrimary,
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "TravelMarket",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Barra de búsqueda con resultados en tiempo real
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = TurquoisePrimary,
            tonalElevation = 0.dp
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onItemClick = { item ->
                        when (item) {
                            is Place -> navController.navigate("${Screen.Detail.route}/${item.id}")
                            is Event -> navController.navigate("${Screen.Detail.route}/${item.id}")
                            is Service -> navController.navigate("${Screen.Detail.route}/${item.id}")
                        }
                    }
                )
            }
        }

        // Contenido principal con scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Mostrar resultados de búsqueda si hay query
            if (showSearchResults) {
                item {
                    SearchResultsSection(
                        searchResults = searchResults,
                        onItemClick = { item ->
                            when (item) {
                                is Place -> navController.navigate("${Screen.Detail.route}/${item.id}")
                                is Event -> navController.navigate("${Screen.Detail.route}/${item.id}")
                                is Service -> navController.navigate("${Screen.Detail.route}/${item.id}")
                            }
                        }
                    )
                }
            } else {
                // Sección de Categorías (solo si no hay búsqueda)
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = "Categorías",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Grid de categorías (2x2)
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                        CategoryCard(
                            category = categories[0],
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate(categories[0].route) }
                        )
                        CategoryCard(
                            category = categories[1],
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate(categories[1].route) }
                        )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                        CategoryCard(
                            category = categories[2],
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate(categories[2].route) }
                        )
                        CategoryCard(
                            category = categories[3],
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate(categories[3].route) }
                        )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Sección de Destacados
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = "Destacados",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Lista de lugares destacados
                items(places.take(3)) { place ->
                    ItemCard(
                        name = place.name,
                        description = place.description,
                        imageRes = place.imageRes,
                        category = place.category,
                        onClick = {
                            navController.navigate("${Screen.Detail.route}/${place.id}")
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Sección que muestra los resultados de búsqueda
 */
@Composable
fun SearchResultsSection(
    searchResults: SearchResult,
    onItemClick: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Resultados de búsqueda (${searchResults.totalResults})",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))
        
        // Lugares encontrados
        if (searchResults.places.isNotEmpty()) {
            Text(
                text = "Lugares (${searchResults.places.size})",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            searchResults.places.forEach { place ->
                ItemCard(
                    name = place.name,
                    description = place.description,
                    imageRes = place.imageRes,
                    category = place.category,
                    onClick = { onItemClick(place) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Eventos encontrados
        if (searchResults.events.isNotEmpty()) {
            Text(
                text = "Eventos (${searchResults.events.size})",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            searchResults.events.forEach { event ->
                ItemCard(
                    name = event.name,
                    description = event.description,
                    imageRes = event.imageRes,
                    category = event.category,
                    onClick = { onItemClick(event) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Servicios encontrados
        if (searchResults.services.isNotEmpty()) {
            Text(
                text = "Servicios (${searchResults.services.size})",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            searchResults.services.forEach { service ->
                ItemCard(
                    name = service.name,
                    description = service.description,
                    imageRes = service.imageRes,
                    category = service.type,
                    onClick = { onItemClick(service) }
                )
            }
        }
        
        // Si no hay resultados
        if (!searchResults.hasResults) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.SearchOff,
                    contentDescription = "Sin resultados",
                    tint = TextLight,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No se encontraron resultados",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary
                )
                Text(
                    text = "Intenta con otros términos de búsqueda",
                    fontSize = 14.sp,
                    color = TextLight
                )
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = category.color
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.name,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}