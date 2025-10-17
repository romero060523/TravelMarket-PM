package com.travelmarket.app.data

import com.travelmarket.app.model.*
import java.util.*

object LocalData {
    
    fun getPlaces(): List<Place> = listOf(
        Place(
            id = "1",
            name = "Estadio Nacional",
            description = "El principal estadio de Lima, sede de los Juegos Panamericanos 2019. Ubicado en el distrito de San Luis, cuenta con capacidad para 50,000 espectadores.",
            imageUrl = "https://via.placeholder.com/300x200/4CAF50/FFFFFF?text=Estadio+Nacional",
            location = "San Luis, Lima",
            category = PlaceCategory.SPORTS,
            rating = 4.8f,
            openingHours = "Lunes a Domingo: 6:00 AM - 10:00 PM",
            contact = "+51 1 234-5678"
        ),
        Place(
            id = "2",
            name = "Plaza Mayor de Lima",
            description = "El corazón histórico de Lima, declarada Patrimonio de la Humanidad por la UNESCO. Rodeada de arquitectura colonial.",
            imageUrl = "https://via.placeholder.com/300x200/FF9800/FFFFFF?text=Plaza+Mayor",
            location = "Centro Histórico, Lima",
            category = PlaceCategory.CULTURAL,
            rating = 4.9f,
            openingHours = "24 horas",
            contact = "+51 1 987-6543"
        ),
        Place(
            id = "3",
            name = "Miraflores",
            description = "Distrito moderno junto al mar, famoso por sus parques, centros comerciales y restaurantes de alta calidad.",
            imageUrl = "https://via.placeholder.com/300x200/2196F3/FFFFFF?text=Miraflores",
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            rating = 4.7f,
            openingHours = "24 horas",
            contact = "+51 1 555-0123"
        ),
        Place(
            id = "4",
            name = "Restaurante Central",
            description = "Uno de los mejores restaurantes del mundo, especializado en cocina peruana contemporánea con ingredientes locales.",
            imageUrl = "https://via.placeholder.com/300x200/E91E63/FFFFFF?text=Central",
            location = "Barranco, Lima",
            category = PlaceCategory.RESTAURANT,
            rating = 4.9f,
            openingHours = "Martes a Sábado: 12:30 PM - 3:30 PM, 7:30 PM - 11:00 PM",
            contact = "+51 1 242-8515"
        )
    )
    
    fun getEvents(): List<Event> = listOf(
        Event(
            id = "1",
            name = "Ceremonia de Apertura Panamericanos",
            description = "Gran ceremonia de apertura de los Juegos Panamericanos con espectáculos culturales y desfile de naciones.",
            imageUrl = "https://via.placeholder.com/300x200/9C27B0/FFFFFF?text=Ceremonia+Apertura",
            location = "Estadio Nacional",
            date = Date(System.currentTimeMillis() + 86400000), // Mañana
            time = "19:00",
            category = EventCategory.SPORTS,
            organizer = "Comité Organizador Panamericanos",
            capacity = 50000
        ),
        Event(
            id = "2",
            name = "Festival Gastronómico Peruano",
            description = "Degustación de platos típicos peruanos preparados por los mejores chefs del país.",
            imageUrl = "https://via.placeholder.com/300x200/FF5722/FFFFFF?text=Festival+Gastronomico",
            location = "Parque Kennedy, Miraflores",
            date = Date(System.currentTimeMillis() + 172800000), // En 2 días
            time = "12:00",
            category = EventCategory.FOOD,
            price = "S/ 25",
            organizer = "Municipalidad de Miraflores"
        ),
        Event(
            id = "3",
            name = "Concierto de Música Andina",
            description = "Presentación de grupos musicales tradicionales peruanos en el Centro Histórico.",
            imageUrl = "https://via.placeholder.com/300x200/795548/FFFFFF?text=Musica+Andina",
            location = "Plaza San Martín",
            date = Date(System.currentTimeMillis() + 259200000), // En 3 días
            time = "18:00",
            category = EventCategory.MUSIC,
            price = "Gratis",
            organizer = "Ministerio de Cultura"
        )
    )
    
    fun getServices(): List<Service> = listOf(
        Service(
            id = "1",
            name = "Metro de Lima",
            description = "Sistema de transporte masivo que conecta los principales distritos de Lima.",
            imageUrl = "https://via.placeholder.com/300x200/607D8B/FFFFFF?text=Metro+Lima",
            category = ServiceCategory.TRANSPORT,
            price = "S/ 2.50",
            rating = 4.2f,
            contact = "0800-12345"
        ),
        Service(
            id = "2",
            name = "Taxi Seguro",
            description = "Servicio de taxi confiable con conductores verificados y tarifas fijas.",
            imageUrl = "https://via.placeholder.com/300x200/FFC107/000000?text=Taxi+Seguro",
            category = ServiceCategory.TRANSPORT,
            price = "Desde S/ 8",
            rating = 4.5f,
            contact = "+51 1 234-5678"
        ),
        Service(
            id = "3",
            name = "Guía Turístico Bilingüe",
            description = "Guías profesionales que hablan español e inglés para tours personalizados.",
            imageUrl = "https://via.placeholder.com/300x200/4CAF50/FFFFFF?text=Guia+Turistico",
            category = ServiceCategory.TOUR_GUIDE,
            price = "S/ 50/hora",
            rating = 4.8f,
            contact = "+51 1 987-6543"
        )
    )
}
