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
fun DetailScreen(placeId: Int?, navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

    // URLs de imágenes para cada lugar
    val imageUrls = mapOf(
        1 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/63/88/e4/getlstd-property-photo.jpg?w=1200&h=-1&s=1",
        2 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/6a/a0/24/20180322-145842-largejpg.jpg?w=900&h=-1&s=1",
        3 to "https://www.inkanmilkyway.com/wp-content/uploads/2019/11/museo-larco-lima-peru.jpg",
        4 to "https://www.inkanmilkyway.com/wp-content/uploads/2019/11/museo-larco-lima-peru.jpg",
        5 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0d/6d/b6/72/tunel-de-las-sorpresas.jpg?w=600&h=400&s=1",
        6 to "https://www.inkanmilkyway.com/wp-content/uploads/2018/08/huaca-pucllana-miraflores-lima-peru.jpg",
        7 to "https://as1.ftcdn.net/jpg/04/85/15/84/1000_F_485158495_LmBAGG8Ydd1GnAL73C3g5Xdvx4Sgc0Mk.jpg",
        8 to "https://www.rumbosdelperu.com/wp-content/uploads/2017/07/15932501839_37b2ac92d7_k-990x556.jpg",
        9 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/5d/3c/9d/parque-de-la-exposicion.jpg?w=1200&h=1200&s=1",
        10 to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTh2h5X_VYa1dt7oVpftIDuuLIjpTp2u1q98w&s",
        11 to "https://upload.wikimedia.org/wikipedia/commons/2/2c/Estaci%C3%B3n_de_Desamparados_o_Casa_de_la_Literatura_Peruana.jpg",
        12 to "https://leyendas.gob.pe/wp-content/uploads/2022/01/PARQUE-DE-LAS-LEYENDAS-002-scaled.jpg",
        13 to "https://www.peru.travel/Contenido/Uploads/Foto-Interna-Iglesia-San-Francisco-y-Catacumbas_638439668363470778.jpg",
        14 to "https://www.infobae.com/resizer/v2/QVE3EFISQBGO3DGWJDMSS75DQY.jpg?auth=08cbb06da86ba00bab2220e338b659e2520b7cabda808fe50294e9fe29b2b664&smart=true&width=350&height=197&quality=85",
        15 to "https://freewalkingtoursperu.com/wp-content/uploads/2019/07/costa-verde-lima-peru-5.jpg",
        16 to "https://upload.wikimedia.org/wikipedia/commons/9/9b/Plaza_Bolivar._Pueblo_Libre%2C_Lima%2C_Per%C3%BA_01.jpg",
        17 to "https://mediaim.expedia.com/destination/9/5949b0729c0e89d2d30aee4c0c5cdbaf.jpg",
        18 to "https://cloudfront-us-east-1.images.arcpublishing.com/infobae/GFSC7U64EFCUVLFG3WYSFZADBQ.jpg",
        19 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/30/a8/40/b1/huaca-huallamrca-san.jpg?w=900&h=500&s=1",
        20 to "https://peru21.pe/sites/default/efsfiles/2024-07/m3aww3r3nnelnfl6sxw7zfflha.jpeg"
    )

    val placeData = when (placeId) {
        1 -> PlaceDetail(
            name = "Estadio Nacional",
            description = "El Estadio Nacional de Lima es la sede principal de los Juegos Panamericanos. Este moderno complejo deportivo cuenta con instalaciones de última generación y ha sido renovado completamente para albergar las competiciones más importantes del evento. Aquí se llevarán a cabo las ceremonias de apertura y clausura, además de las competencias de atletismo.",
            imageRes = R.drawable.map,
            imageUrl = imageUrls[1],
            location = "Av. José Díaz, Cercado de Lima, Perú",
            schedule = "Eventos diarios de 9:00 AM - 10:00 PM",
            category = "Eventos"
        )
        2 -> PlaceDetail(
            name = "Parque Kennedy",
            description = "El Parque Kennedy es uno de los espacios públicos más emblemáticos de Miraflores, Lima. Famoso por su gastronomía, vida nocturna y ambiente bohemio. Es el lugar perfecto para disfrutar de la comida peruana, especialmente los anticuchos y picarones, mientras se disfruta del ambiente cultural y artístico del lugar.",
            imageRes = R.drawable.map,
            imageUrl = imageUrls[2],
            location = "Miraflores, Lima",
            schedule = "Abierto 24 horas",
            category = "Gastronomía"
        )
        3 -> PlaceDetail(
            name = "Museo Larco",
            description = "El Museo Larco es uno de los museos más importantes de América Latina, ubicado en Lima, Perú. Alberga una de las colecciones arqueológicas precolombinas más destacadas del mundo, con más de 45,000 piezas que abarcan 5,000 años de historia peruana. El museo es famoso por su galería de arte erótico precolombino y sus hermosos jardines.",
            imageRes = R.drawable.map,
            imageUrl = imageUrls[3],
            location = "Pueblo Libre, Lima",
            schedule = "9:00 AM - 10:00 PM",
            category = "Cultura"
        )
        else -> PlaceDetail(
            name = "Lugar no encontrado",
            description = "No se encontró información para este lugar.",
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