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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.data.RepositoryProvider
import com.tecsup.travelmarket.data.TravelViewModel
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: TravelViewModel
) {
    // ✅ Observar favoritos del ViewModel
    val favoritePlaces by viewModel.favoritePlaces.collectAsState()
    val favoriteEvents by viewModel.favoriteEvents.collectAsState()
    val favoriteServices by viewModel.favoriteServices.collectAsState()

    // ✅ Calcular total de favoritos
    val totalFavorites = favoritePlaces.size + favoriteEvents.size + favoriteServices.size

    // ✅ Efecto para recargar favoritos cuando se muestra la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadAllFavorites()
    }

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
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Favoritos",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Mis Favoritos",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (totalFavorites > 0) {
                        Text(
                            text = "$totalFavorites elementos guardados",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        // ✅ Si no hay favoritos - mostrar estado vacío
        if (totalFavorites == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGray)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Sin favoritos",
                    tint = TextLight,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No tienes favoritos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Explora lugares, eventos y servicios",
                    fontSize = 14.sp,
                    color = TextLight,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "y guarda tus favoritos aquí",
                    fontSize = 14.sp,
                    color = TextLight,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón para ir a Home
                Button(
                    onClick = { navController.navigate(Screen.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TurquoisePrimary
                    )
                ) {
                    Icon(
                        Icons.Default.Explore,
                        contentDescription = "Explorar",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Explorar ahora",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else {
            // ✅ Si hay favoritos - mostrar lista
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGray)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // ========== LUGARES FAVORITOS ==========
                if (favoritePlaces.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Place,
                                contentDescription = "Lugares",
                                tint = TurquoisePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Lugares (${favoritePlaces.size})",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    }

                    items(favoritePlaces) { place ->
                        ItemCard(
                            name = place.name,
                            description = place.description,
                            imageRes = place.imageRes,
                            category = place.category,
                            onClick = {
                                navController.navigate("${Screen.Detail.route}/place/${place.id}")
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // ========== EVENTOS FAVORITOS ==========
                if (favoriteEvents.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Event,
                                contentDescription = "Eventos",
                                tint = OrangeSecondary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Eventos (${favoriteEvents.size})",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    }

                    items(favoriteEvents) { event ->
                        ItemCard(
                            name = event.name,
                            description = event.description,
                            imageRes = event.imageRes,
                            category = event.category,
                            onClick = {
                                navController.navigate("${Screen.Detail.route}/event/${event.id}")
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // ========== SERVICIOS FAVORITOS ==========
                if (favoriteServices.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Restaurant,
                                contentDescription = "Servicios",
                                tint = TurquoisePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Servicios (${favoriteServices.size})",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    }

                    items(favoriteServices) { service ->
                        ItemCard(
                            name = service.name,
                            description = service.description,
                            imageRes = service.imageRes,
                            category = service.type,
                            onClick = {
                                navController.navigate("${Screen.Detail.route}/service/${service.id}")
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // ========== MENSAJE INFORMATIVO AL FINAL ==========
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = BackgroundWhite
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Info,
                                contentDescription = "Info",
                                tint = TurquoisePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Toca el ❤️ en cualquier elemento para quitarlo de favoritos",
                                fontSize = 13.sp,
                                color = TextSecondary,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp)) // Espacio para el BottomNav
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