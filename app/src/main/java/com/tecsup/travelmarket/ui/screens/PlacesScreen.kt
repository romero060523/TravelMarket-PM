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
import com.tecsup.travelmarket.data.LocalData
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(navController: NavController) {
    val placesList = LocalData.places

    // ✅ Mapa de URLs de imágenes por ID
    val imageUrls = mapOf(
        1 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/63/88/e4/getlstd-property-photo.jpg?w=1200&h=-1&s=1",
        2 to "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Plaza_Mayor_de_Lima_2019.jpg/1200px-Plaza_Mayor_de_Lima_2019.jpg",
        3 to "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/12/6a/a0/24/20180322-145842-largejpg.jpg?w=900&h=-1&s=1",
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

        // Lista de lugares con URLs de imágenes
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundGray)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(placesList) { place ->
                ItemCard(
                    name = place.name,
                    description = place.description,
                    imageRes = place.imageRes,
                    category = place.category,
                    imageUrl = imageUrls[place.id], // ✅ Pasar URL de imagen
                    onClick = {
                        navController.navigate("${Screen.Detail.route}/place/${place.id}")
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
