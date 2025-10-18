package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.travelmarket.R

/**
 * Pantalla de detalle que muestra información completa de un lugar o evento
 * Backend básico sin estilos personalizados
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(placeId: Int?, navController: NavController) {
    // Estado para controlar si el lugar está en favoritos
    var isFavorite by remember { mutableStateOf(false) }
    
    // Datos del lugar según el ID
    val placeData = when (placeId) {
        1 -> PlaceDetail(
            name = "Estadio Nacional",
            description = "El Estadio Nacional de Lima es la sede principal de los Juegos Panamericanos. Este moderno complejo deportivo cuenta con instalaciones de última generación y ha sido renovado completamente para albergar las competiciones más importantes del evento. Aquí se llevarán a cabo las ceremonias de apertura y clausura, además de las competencias de atletismo.",
            imageRes = R.drawable.map,
            location = "Av. José Díaz, Cercado de Lima, Perú",
            schedule = "Eventos diarios de 9:00 AM - 10:00 PM",
            category = "Eventos"
        )
        2 -> PlaceDetail(
            name = "Parque Kennedy",
            description = "El Parque Kennedy es uno de los espacios públicos más emblemáticos de Miraflores, Lima. Famoso por su gastronomía, vida nocturna y ambiente bohemio. Es el lugar perfecto para disfrutar de la comida peruana, especialmente los anticuchos y picarones, mientras se disfruta del ambiente cultural y artístico del lugar.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            schedule = "Abierto 24 horas",
            category = "Gastronomía"
        )
        3 -> PlaceDetail(
            name = "Museo Larco",
            description = "El Museo Larco es uno de los museos más importantes de América Latina, ubicado en Lima, Perú. Alberga una de las colecciones arqueológicas precolombinas más destacadas del mundo, con más de 45,000 piezas que abarcan 5,000 años de historia peruana. El museo es famoso por su galería de arte erótico precolombino y sus hermosos jardines.",
            imageRes = R.drawable.map,
            location = "Pueblo Libre, Lima",
            schedule = "9:00 AM - 10:00 PM",
            category = "Cultura"
        )
        else -> PlaceDetail(
            name = "Lugar no encontrado",
            description = "No se encontró información para este lugar.",
            imageRes = R.drawable.map,
            location = "Ubicación desconocida",
            schedule = "No disponible",
            category = "N/A"
        )
    }

    // Scaffold principal
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(placeData.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver atrás")
                    }
                },
                actions = {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorito"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen del lugar
            Image(
                painter = painterResource(id = placeData.imageRes),
                contentDescription = "Imagen de ${placeData.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            
            // Contenido de información
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Título y categoría
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = placeData.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = placeData.category,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Descripción
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = placeData.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Tarjeta de horario
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Horario"
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column {
                            Text(
                                text = "Horario",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = placeData.schedule,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                
                // Tarjeta de ubicación
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Ubicación"
                        )
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column {
                            Text(
                                text = "Ubicación",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = placeData.location,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Botones de acción
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Botón de favoritos
                    Button(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorito"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isFavorite) "En favoritos" else "Guardar en favoritos"
                        )
                    }
                    
                    // Botón de volver al inicio
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Volver al inicio")
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

/**
 * Data class para representar los detalles de un lugar
 */
data class PlaceDetail(
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val schedule: String,
    val category: String
)