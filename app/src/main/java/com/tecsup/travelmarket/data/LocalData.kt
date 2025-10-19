package com.tecsup.travelmarket.data

import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.model.*

//Datos locales con informacion simulada, contiene listas de lugares, eventos y servicios
object LocalData {

    // Lugares turisticos
    val places = listOf(
        Place(
            id = 1,
            name = "Estadio Nacional",
            description = "El Estadio Nacional de Lima es la sede principal de los Juegos Panamericanos. Este moderno complejo deportivo cuenta con instalaciones de última generación y ha sido renovado completamente para albergar las competiciones más importantes del evento. Aquí se llevarán a cabo las ceremonias de apertura y clausura, además de las competencias de atletismo.",
            imageRes = R.drawable.map,
            location = "Av. José Díaz, Cercado de Lima, Perú",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.8
        ),
        Place(
            id = 2,
            name = "Centro Histórico de Lima",
            description = "Plaza de Armas y arquitectura colonial declarada Patrimonio de la Humanidad por la UNESCO. El centro histórico alberga importantes monumentos como la Catedral de Lima, el Palacio de Gobierno y hermosas casonas coloniales que narran la historia de la ciudad.",
            imageRes = R.drawable.map,
            location = "Plaza Mayor, Cercado de Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.9
        ),
        Place(
            id = 3,
            name = "Parque Kennedy",
            description = "Uno de los espacios públicos más emblemáticos de Miraflores. Famoso por sus gatos, artesanos y ambiente bohemio. Es el corazón del distrito y punto de encuentro para locales y turistas.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.5
        ),
        Place(
            id = 4,
            name = "Museo Larco",
            description = "Uno de los museos más importantes de América Latina. Alberga una colección arqueológica precolombina de más de 45,000 piezas que abarcan 5,000 años de historia peruana. Destaca su galería de arte erótico y sus hermosos jardines.",
            imageRes = R.drawable.map,
            location = "Pueblo Libre, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.9
        ),
        Place(
            id = 5,
            name = "Circuito Mágico del Agua",
            description = "Complejo de fuentes ornamentales interactivas y espectáculos de agua, luz y música. Es el complejo de fuentes más grande del mundo en un parque público, con 13 fuentes distintas.",
            imageRes = R.drawable.map,
            location = "Parque de la Reserva, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "3:00 PM - 10:30 PM",
            rating = 4.7
        ),
        Place(
            id = 6,
            name = "Huaca Pucllana",
            description = "Importante sitio arqueológico de la cultura Lima construido hace 1,500 años. Esta pirámide de adobe ofrece un contraste único entre la Lima antigua y moderna.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.6
        )
    )

    //Lista de eventos en Lima
    val events = listOf(
        Event(
            id = 1,
            name = "Juegos Panamericanos Lima 2019",
            description = "El evento deportivo más importante de América, con competencias de atletismo, natación, gimnasia y más de 40 disciplinas deportivas. Lima es sede de este prestigioso evento que reúne a los mejores atletas del continente.",
            imageRes = R.drawable.map,
            location = "Estadio Nacional, Lima",
            date = "26/07/2019",
            time = "9:00 AM",
            schedule = "9:00 AM - 10:00 PM",
            category = EventCategory.SPORTS,
            price = 50.0,
            capacity = 50000
        ),
        Event(
            id = 2,
            name = "Festival Gastronómico Mistura",
            description = "La feria gastronómica más importante de América Latina. Reúne a los mejores chefs, productores y amantes de la comida peruana en una celebración de sabores, tradiciones y cultura culinaria.",
            imageRes = R.drawable.map,
            location = "Costa Verde, Magdalena",
            date = "15/09/2024",
            time = "11:00 AM",
            schedule = "11:00 AM - 9:00 PM",
            category = EventCategory.FESTIVAL,
            price = 30.0,
            capacity = 10000
        ),
        Event(
            id = 3,
            name = "Concierto de Gianmarco",
            description = "El cantautor peruano más reconocido presenta su nuevo álbum en un concierto único. Una noche llena de música, emoción y los mejores éxitos de su carrera.",
            imageRes = R.drawable.map,
            location = "Arena Perú, Surco",
            date = "20/11/2024",
            time = "8:00 PM",
            schedule = "8:00 PM - 11:00 PM",
            category = EventCategory.CONCERT,
            price = 120.0,
            capacity = 5000
        ),
        Event(
            id = 4,
            name = "Feria del Libro Ricardo Palma",
            description = "La feria del libro más importante del Perú. Presentaciones de autores, venta de libros, conferencias y actividades culturales para toda la familia.",
            imageRes = R.drawable.map,
            location = "Parque Próceres, Jesús María",
            date = "25/10/2024",
            time = "10:00 AM",
            schedule = "10:00 AM - 9:00 PM",
            category = EventCategory.EXHIBITION,
            price = 0.0,
            capacity = 2000
        )
    )

    // Lista para servicios disponibles
    val services = listOf(
        Service(
            id = 1,
            name = "Central Restaurante",
            description = "Restaurante galardonado como el mejor de América Latina. Ofrece una experiencia culinaria única basada en los ingredientes y ecosistemas del Perú, desde el Pacífico hasta los Andes.",
            imageRes = R.drawable.map,
            type = "Gastronomía",
            contact = "+51 1 242 8515",
            location = "Barranco, Lima",
            category = ServiceCategory.GASTRONOMY,
            priceRange = "$$$",
            price = 150.0,
            schedule = "12:45 PM - 3:00 PM, 7:45 PM - 10:00 PM"
        ),
        Service(
            id = 2,
            name = "Maido Restaurante",
            description = "Fusión nikkei de alta cocina. El chef Mitsuharu Tsumura combina ingredientes peruanos con técnicas japonesas para crear platos innovadores y deliciosos.",
            imageRes = R.drawable.map,
            type = "Gastronomía",
            contact = "+51 1 446 2512",
            location = "Miraflores, Lima",
            category = ServiceCategory.GASTRONOMY,
            priceRange = "$$$",
            price = 120.0,
            schedule = "1:00 PM - 3:00 PM, 7:30 PM - 11:00 PM"
        ),
        Service(
            id = 3,
            name = "Tío Mario Anticuchos",
            description = "El mejor lugar para probar anticuchos tradicionales peruanos. Comida callejera auténtica con más de 30 años de tradición familiar.",
            imageRes = R.drawable.map,
            type = "Gastronomía",
            contact = "+51 999 888 777",
            location = "Jesús María, Lima",
            category = ServiceCategory.GASTRONOMY,
            priceRange = "$",
            price = 15.0,
            schedule = "6:00 PM - 12:00 AM"
        ),
        Service(
            id = 4,
            name = "Metropolitano",
            description = "Sistema de transporte rápido en buses articulados. Conecta el norte y sur de Lima con carriles exclusivos. Es la forma más rápida y segura de moverse por la ciudad.",
            imageRes = R.drawable.map,
            type = "Transporte",
            contact = "www.metropolitano.gob.pe",
            location = "Varias estaciones, Lima",
            category = ServiceCategory.TRANSPORT,
            priceRange = "$",
            price = 2.5,
            schedule = "5:00 AM - 10:00 PM"
        ),
        Service(
            id = 5,
            name = "Lima Airport Partners - Taxi",
            description = "Servicio oficial de taxis del Aeropuerto Jorge Chávez. Tarifas fijas, seguros y confiables para trasladarte desde el aeropuerto a cualquier punto de Lima.",
            imageRes = R.drawable.map,
            type = "Transporte",
            contact = "+51 1 517 3100",
            location = "Aeropuerto Jorge Chávez",
            category = ServiceCategory.TRANSPORT,
            priceRange = "$$",
            price = 25.0,
            schedule = "24 horas"
        ),
        Service(
            id = 6,
            name = "Café del Museo",
            description = "Cafetería ubicada en el Museo Larco. Ofrece café peruano de especialidad, postres artesanales y un ambiente rodeado de jardines coloniales.",
            imageRes = R.drawable.map,
            type = "Gastronomía",
            contact = "+51 1 462 4757",
            location = "Pueblo Libre, Lima",
            category = ServiceCategory.GASTRONOMY,
            priceRange = "$$",
            price = 35.0,
            schedule = "9:00 AM - 10:00 PM"
        )
    )

    // Usuario para ejemplo de pruebas
    val currentUser = User(
        id = 1,
        name = "Usuario Demo",
        email = "demo@travelmarket.com",
        phone = "+51 999 888 777"
    )
}