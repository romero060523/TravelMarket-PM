package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.TravelViewModel
import com.tecsup.travelmarket.model.Event
import com.tecsup.travelmarket.model.Place
import com.tecsup.travelmarket.model.Service
import com.tecsup.travelmarket.ui.theme.*

data class PlaceDetail(
    val name: String,
    val description: String,
    val imageRes: Int,
    val imageUrl: String? = null,
    val location: String,
    val schedule: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    placeId: Int?,
    navController: NavController,
    type: String? = null,
    viewModel: TravelViewModel // ✅ Recibir ViewModel
) {
    // ✅ Obtener item del ViewModel según el tipo
    val item = remember(placeId, type) {
        placeId?.let { id ->
            when (type?.lowercase()) {
                "place" -> viewModel.getPlaceById(id)
                "event" -> viewModel.getEventById(id)
                "service" -> viewModel.getServiceById(id)
                else -> null
            }
        }
    }

    // ✅ Estado de favorito reactivo
    var isFavorite by remember(placeId, type) {
        mutableStateOf(
            if (placeId != null && type != null) {
                viewModel.isFavorite(placeId, type)
            } else {
                false
            }
        )
    }

    // ✅ Convertir el item a PlaceDetail para mostrar
    val placeData: PlaceDetail = when (item) {
        is Place -> PlaceDetail(
            name = item.name,
            description = item.description,
            imageRes = item.imageRes,
            location = item.location,
            schedule = item.schedule,
            category = item.category
        )
        is Event -> PlaceDetail(
            name = item.name,
            description = item.description,
            imageRes = item.imageRes,
            location = item.location,
            schedule = item.date,
            category = item.category
        )
        is Service -> PlaceDetail(
            name = item.name,
            description = item.description,
            imageRes = item.imageRes,
            location = item.location,
            schedule = item.schedule,
            category = item.category
        )
        else -> PlaceDetail(
            name = "Elemento no encontrado",
            description = "No se encontró información para este elemento.",
            imageRes = R.drawable.map,
            imageUrl = null,
            location = "Ubicación desconocida",
            schedule = "No disponible",
            category = "N/A"
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen principal con overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                if (placeData.imageUrl != null) {
                    AsyncImage(
                        model = placeData.imageUrl,
                        contentDescription = "Imagen de ${placeData.name}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = placeData.imageRes),
                        placeholder = painterResource(id = placeData.imageRes)
                    )
                } else {
                    Image(
                        painter = painterResource(id = placeData.imageRes),
                        contentDescription = "Imagen de ${placeData.name}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Botones flotantes sobre la imagen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón de volver
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = BlueAccent
                        )
                    }

                    // ✅ Botón de favorito con funcionalidad
                    IconButton(
                        onClick = {
                            if (placeId != null && type != null) {
                                viewModel.toggleFavorite(placeId, type)
                                isFavorite = !isFavorite
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (isFavorite) Color.Red else BlueAccent
                        )
                    }
                }
            }

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundWhite)
                    .padding(20.dp)
            ) {
                // Título y badge de categoría
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = placeData.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.weight(1f)
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = TurquoisePrimary
                    ) {
                        Text(
                            text = placeData.category,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sección Descripción
                Text(
                    text = "Descripción",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = placeData.description,
                    fontSize = 14.sp,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Card de Horario
                InfoCard(
                    icon = Icons.Default.AccessTime,
                    title = "Horario",
                    content = placeData.schedule,
                    iconColor = TurquoisePrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Card de Ubicación
                InfoCard(
                    icon = Icons.Default.LocationOn,
                    title = "Ubicación",
                    content = placeData.location,
                    iconColor = OrangeSecondary
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ✅ Botón de acción principal con funcionalidad
                Button(
                    onClick = {
                        if (placeId != null && type != null) {
                            viewModel.toggleFavorite(placeId, type)
                            isFavorite = !isFavorite
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFavorite) Color.Red else TurquoisePrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isFavorite) "Quitar de favoritos" else "Guardar en favoritos",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botón secundario
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Volver al inicio",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun InfoCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    content: String,
    iconColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundGray
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono con fondo circular
            Surface(
                shape = CircleShape,
                color = iconColor.copy(alpha = 0.1f),
                modifier = Modifier.size(44.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Contenido de texto
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = content,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
