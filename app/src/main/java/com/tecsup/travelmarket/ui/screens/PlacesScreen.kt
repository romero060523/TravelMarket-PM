package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.LocalData
import com.tecsup.travelmarket.model.Place
import com.tecsup.travelmarket.model.PlaceCategory
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    // Lista de lugares con las URLs de imágenes proporcionadas
    val placesList = listOf(
        Place(
            id = 1,
            name = "Estadio Nacional",
            description = "El Estadio Nacional de Lima es la sede principal de los Juegos Panamericanos y uno de los complejos deportivos más modernos de América Latina. Con una capacidad para 40,000 espectadores, cuenta con instalaciones de última generación, pista de atletismo de 9 carriles, campo de fútbol reglamentario y sistemas de iluminación LED de alta tecnología.",
            imageRes = R.drawable.map,
            location = "Av. José Díaz, Cercado de Lima, Perú",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.8
        ),
        Place(
            id = 2,
            name = "Centro Histórico de Lima",
            description = "El Centro Histórico de Lima es un tesoro arquitectónico declarado Patrimonio de la Humanidad por la UNESCO en 1988. Este impresionante conjunto urbano alberga más de 600 monumentos coloniales que datan de los siglos XVI al XIX.",
            imageRes = R.drawable.map,
            location = "Plaza Mayor, Cercado de Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.9
        ),
        Place(
            id = 3,
            name = "Parque Kennedy",
            description = "El Parque Kennedy es el corazón palpitante de Miraflores y uno de los espacios públicos más emblemáticos de Lima. Este encantador parque debe su nombre al expresidente estadounidense John F. Kennedy y es famoso por su singular población de gatos callejeros.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.5
        ),
        Place(
            id = 4,
            name = "Museo Larco",
            description = "El Museo Larco es considerado uno de los museos arqueológicos más importantes de América Latina y una joya cultural del Perú. Ubicado en una hermosa casona virreinal del siglo XVIII, alberga una impresionante colección de más de 45,000 piezas arqueológicas.",
            imageRes = R.drawable.map,
            location = "Pueblo Libre, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.9
        ),
        Place(
            id = 5,
            name = "Circuito Mágico del Agua",
            description = "El Circuito Mágico del Agua es un espectacular complejo de fuentes ornamentales que ha sido reconocido por el Libro Guinness de los Récords como el complejo de fuentes más grande del mundo en un parque público.",
            imageRes = R.drawable.map,
            location = "Parque de la Reserva, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "3:00 PM - 10:30 PM",
            rating = 4.7
        ),
        Place(
            id = 6,
            name = "Huaca Pucllana",
            description = "La Huaca Pucllana es un impresionante sitio arqueológico de la cultura Lima que data del siglo V d.C., construido hace más de 1,500 años. Esta majestuosa pirámide de adobe de 25 metros de altura ofrece un contraste fascinante entre la Lima prehispánica y la moderna ciudad.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.6
        ),
        Place(
            id = 7,
            name = "Malecón de Miraflores",
            description = "El Malecón de Miraflores es uno de los paseos costeros más hermosos y románticos de Lima, extendiéndose por más de 6 kilómetros a lo largo de los acantilados con vistas espectaculares al Océano Pacífico.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.8
        ),
        Place(
            id = 8,
            name = "Plaza San Martín",
            description = "La Plaza San Martín es una de las plazas más importantes y emblemáticas del centro histórico de Lima, declarada Patrimonio Cultural de la Nación. Esta majestuosa plaza está rodeada de impresionantes edificios coloniales y republicanos.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.4
        ),
        Place(
            id = 9,
            name = "Museo de Arte de Lima (MALI)",
            description = "El Museo de Arte de Lima (MALI) es la institución cultural más importante del Perú y uno de los museos de arte más prestigiosos de América Latina. Ubicado en el hermoso Parque de la Exposición, el MALI alberga una colección excepcional de más de 17,000 obras de arte.",
            imageRes = R.drawable.map,
            location = "Parque de la Exposición, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "10:00 AM - 7:00 PM",
            rating = 4.7
        ),
        Place(
            id = 10,
            name = "Barranco",
            description = "Barranco es el distrito bohemio y artístico más encantador de Lima, conocido como el 'Soho limeño'. Este pintoresco barrio costero combina la elegancia colonial con la modernidad artística, ofreciendo un ambiente único lleno de galerías de arte, cafés bohemios y bares de moda.",
            imageRes = R.drawable.map,
            location = "Barranco, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.6
        ),
        Place(
            id = 11,
            name = "Casa de la Literatura Peruana",
            description = "La Casa de la Literatura Peruana es un centro cultural único ubicado en la hermosa antigua Estación de Desamparados, un edificio neoclásico del siglo XIX que ha sido restaurado para albergar la literatura peruana.",
            imageRes = R.drawable.map,
            location = "Centro Histórico, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "10:00 AM - 7:00 PM",
            rating = 4.5
        ),
        Place(
            id = 12,
            name = "Parque de las Leyendas",
            description = "El Parque de las Leyendas es el zoológico más grande e importante del Perú y un parque temático único que recrea las diferentes regiones geográficas y culturas prehispánicas del país. Con más de 1,200 animales de 200 especies.",
            imageRes = R.drawable.map,
            location = "San Miguel, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.3
        ),
        Place(
            id = 13,
            name = "Convento de San Francisco",
            description = "El Convento de San Francisco es uno de los complejos religiosos coloniales más importantes de Lima y un tesoro arquitectónico del siglo XVI. Este majestuoso conjunto incluye la basílica, el convento y la biblioteca, destacando por su impresionante arquitectura barroca limeña.",
            imageRes = R.drawable.map,
            location = "Centro Histórico, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.7
        ),
        Place(
            id = 14,
            name = "Mercado Central de Lima",
            description = "El Mercado Central de Lima es el mercado más grande, tradicional y auténtico de la capital peruana, un verdadero tesoro gastronómico y cultural. Con más de 100 años de historia, este mercado bullicioso ofrece una experiencia sensorial única.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "6:00 AM - 6:00 PM",
            rating = 4.2
        ),
        Place(
            id = 15,
            name = "Costa Verde",
            description = "La Costa Verde es el extenso malecón costero más hermoso de Lima, que se extiende por más de 20 kilómetros a lo largo de los acantilados del Océano Pacífico, conectando los distritos de Miraflores, Barranco, Chorrillos y otros.",
            imageRes = R.drawable.map,
            location = "Miraflores, Barranco, Chorrillos",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.6
        ),
        Place(
            id = 16,
            name = "Museo Nacional de Arqueología",
            description = "Museo que exhibe la historia arqueológica del Perú. Incluye piezas de las culturas Chavín, Moche, Nazca, Inca y otras civilizaciones prehispánicas.",
            imageRes = R.drawable.map,
            location = "Pueblo Libre, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.4
        ),
        Place(
            id = 17,
            name = "Plaza de Armas",
            description = "Plaza principal de Lima, corazón del centro histórico. Rodeada por la Catedral, Palacio de Gobierno y otros edificios coloniales importantes.",
            imageRes = R.drawable.map,
            location = "Centro Histórico, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.8
        ),
        Place(
            id = 18,
            name = "Larcomar",
            description = "Centro comercial y de entretenimiento ubicado en los acantilados de Miraflores. Ofrece tiendas, restaurantes y una vista espectacular al océano.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "10:00 AM - 10:00 PM",
            rating = 4.5
        ),
        Place(
            id = 19,
            name = "Huaca Huallamarca",
            description = "Sitio arqueológico prehispánico en San Isidro. Pirámide de adobe que muestra la arquitectura de las culturas Lima e Inca.",
            imageRes = R.drawable.map,
            location = "San Isidro, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.1
        ),
        Place(
            id = 20,
            name = "Parque de la Reserva",
            description = "Parque público que alberga el Circuito Mágico del Agua. Espacio verde ideal para caminar y disfrutar de las fuentes ornamentales.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "6:00 AM - 10:00 PM",
            rating = 4.4
        )
    )

    // URLs de imágenes para cada lugar
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

    val categories = listOf("Todos", PlaceCategory.TOURIST_ATTRACTION, PlaceCategory.HISTORICAL, PlaceCategory.CULTURE)

    // Filtrar lugares según búsqueda y categoría
    val filteredPlaces = placesList.filter { place ->
        val matchesSearch = searchQuery.isEmpty() || 
            place.name.contains(searchQuery, ignoreCase = true) ||
            place.description.contains(searchQuery, ignoreCase = true) ||
            place.location.contains(searchQuery, ignoreCase = true)
        
        val matchesCategory = selectedCategory == "Todos" || place.category == selectedCategory
        
        matchesSearch && matchesCategory
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Lugares Turísticos",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = BlueAccent
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundWhite
            )
        )

        // Barra de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = {
                Text(
                    text = "Buscar lugares...",
                    color = TextSecondary
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = TextSecondary
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Limpiar",
                            tint = TextSecondary
                        )
                    }
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { keyboardController?.hide() }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TurquoisePrimary,
                unfocusedBorderColor = BackgroundGray
            )
        )

        // Filtros de categoría
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                FilterChip(
                    onClick = { selectedCategory = category },
                    label = {
                        Text(
                            text = category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    selected = selectedCategory == category,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = TurquoisePrimary,
                        selectedLabelColor = Color.White,
                        containerColor = BackgroundGray,
                        labelColor = TextSecondary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        
        // Lista de lugares
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredPlaces) { place ->
                PlaceCard(
                    place = place,
                    imageUrl = imageUrls[place.id],
                    onClick = {
                        navController.navigate("${Screen.Detail.route}/place/${place.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun PlaceCard(
    place: Place,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Imagen del lugar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Imagen de ${place.name}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = place.imageRes),
                        placeholder = painterResource(id = place.imageRes)
                    )
                } else {
                    Image(
                        painter = painterResource(id = place.imageRes),
                        contentDescription = "Imagen de ${place.name}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Badge de categoría
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(8.dp),
                    color = TurquoisePrimary
                ) {
                    Text(
                        text = place.category,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Rating
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Black.copy(alpha = 0.7f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = place.rating.toString(),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Contenido de la card
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Nombre del lugar
                Text(
                    text = place.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Ubicación
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Ubicación",
                        tint = OrangeSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = place.location,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Descripción
                Text(
                    text = place.description,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Horario
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AccessTime,
                        contentDescription = "Horario",
                        tint = TurquoisePrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = place.schedule,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}