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
import com.tecsup.travelmarket.data.RepositoryProvider
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController? = null) {
    // Obtener el repositorio
    val repository = remember { RepositoryProvider.repository }

    // Estado para forzar recomposición cuando cambien los favoritos
    var refreshTrigger by remember { mutableStateOf(0) }

    // Obtener favoritos del repositorio
    val favoritePlaces = remember(refreshTrigger) {
        repository.getFavoritePlaces()
    }

    val hasFavorites = favoritePlaces.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {
        // Header
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = TurquoisePrimary,
            tonalElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favoritos",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Mis Favoritos",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (hasFavorites) {
                            Text(
                                text = "${favoritePlaces.size} lugar${if (favoritePlaces.size != 1) "es" else ""}",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // Contenido
        if (!hasFavorites) {
            // Estado vacío
            EmptyFavoritesView(navController)
        } else {
            // Lista de favoritos
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGray)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Información útil
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = TurquoisePrimary.copy(alpha = 0.1f)
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Info",
                                tint = TurquoisePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Toca el corazón en los detalles para agregar o quitar favoritos",
                                fontSize = 13.sp,
                                color = TextPrimary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Lista de lugares favoritos
                items(favoritePlaces) { place ->
                    ItemCard(
                        name = place.name,
                        description = place.description,
                        imageRes = place.imageRes,
                        category = place.category,
                        onClick = {
                            navController?.navigate("${Screen.Detail.route}/${place.id}")
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesView(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono grande
        Surface(
            shape = androidx.compose.foundation.shape.CircleShape,
            color = BackgroundGray,
            modifier = Modifier.size(120.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Sin favoritos",
                    tint = TextLight,
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "No tienes favoritos aún",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Explora lugares increíbles y guárdalos aquí para acceder fácilmente",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para explorar
        Button(
            onClick = {
                navController?.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TurquoisePrimary
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Explore,
                contentDescription = "Explorar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Explorar lugares",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}