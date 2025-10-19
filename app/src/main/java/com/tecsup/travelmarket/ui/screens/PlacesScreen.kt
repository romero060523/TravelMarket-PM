package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.data.Repository
import com.tecsup.travelmarket.model.*
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(navController: NavController) {
    val repository = remember { Repository() }
    val places = repository.getAllPlaces()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {
        // Header con título y botón de regreso
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = TurquoisePrimary,
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lugares Turísticos",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        // Lista de lugares
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            items(places) { place ->
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
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
