package com.travelmarket.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.travelmarket.app.data.TravelViewModel
import com.travelmarket.app.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    placeId: String,
    navController: androidx.navigation.NavHostController,
    viewModel: TravelViewModel = viewModel()
) {
    val place = viewModel.getPlaceById(placeId)
    
    if (place == null) {
        ErrorView(
            message = "Lugar no encontrado",
            onRetry = { navController.popBackStack() }
        )
        return
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(place.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.togglePlaceFavorite(place.id) }
                    ) {
                        Icon(
                            imageVector = if (place.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (place.isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                            tint = if (place.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
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
            // Imagen principal
            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Información básica
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Ubicación",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    Text(
                        text = place.location,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Calificación",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                        
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        Text(
                            text = place.rating.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Descripción
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = place.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Información adicional
                if (place.openingHours != null) {
                    InfoCard(
                        icon = Icons.Default.Info,
                        title = "Horarios",
                        content = place.openingHours
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                if (place.contact != null) {
                    InfoCard(
                        icon = Icons.Default.Phone,
                        title = "Contacto",
                        content = place.contact
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Botón de acción
                Button(
                    onClick = { /* TODO: Implementar acción */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Ver en mapa",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
