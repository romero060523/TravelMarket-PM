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
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.ui.theme.*

data class PlaceDetail(
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: String,
    val schedule: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(placeId: Int?, navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

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
                Image(
                    painter = painterResource(id = placeData.imageRes),
                    contentDescription = "Imagen de ${placeData.name}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

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

                    // Botón de favorito
                    IconButton(
                        onClick = { isFavorite = !isFavorite },
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

                // Botón de acción principal
                Button(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TurquoisePrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Guardar en favoritos",
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