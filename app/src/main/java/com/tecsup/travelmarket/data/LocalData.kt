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
            description = "El Estadio Nacional de Lima es la sede principal de los Juegos Panamericanos y uno de los complejos deportivos más modernos de América Latina. Con una capacidad para 40,000 espectadores, cuenta con instalaciones de última generación, pista de atletismo de 9 carriles, campo de fútbol reglamentario y sistemas de iluminación LED de alta tecnología. El estadio ha sido completamente renovado para albergar competencias internacionales y ofrece una experiencia única para los amantes del deporte. Además de eventos deportivos, también alberga conciertos y espectáculos culturales.",
            imageRes = R.drawable.map,
            location = "Av. José Díaz, Cercado de Lima, Perú",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.8
        ),
        Place(
            id = 2,
            name = "Centro Histórico de Lima",
            description = "El Centro Histórico de Lima es un tesoro arquitectónico declarado Patrimonio de la Humanidad por la UNESCO en 1988. Este impresionante conjunto urbano alberga más de 600 monumentos coloniales que datan de los siglos XVI al XIX. Destacan la majestuosa Catedral de Lima con su arquitectura barroca, el Palacio de Gobierno donde reside el Presidente de la República, y el Convento de San Francisco con sus misteriosas catacumbas. Las hermosas casonas coloniales, balcones de madera tallada y plazas empedradas transportan a los visitantes a la época virreinal, ofreciendo un viaje único por la historia del Perú.",
            imageRes = R.drawable.map,
            location = "Plaza Mayor, Cercado de Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.9
        ),
        Place(
            id = 3,
            name = "Parque Kennedy",
            description = "El Parque Kennedy es el corazón palpitante de Miraflores y uno de los espacios públicos más emblemáticos de Lima. Este encantador parque debe su nombre al expresidente estadounidense John F. Kennedy y es famoso por su singular población de gatos callejeros que han encontrado refugio en este espacio verde. Los visitantes pueden disfrutar de artesanías locales, música en vivo, artistas callejeros y un ambiente bohemio único. El parque está rodeado de cafés, restaurantes y tiendas, convirtiéndose en el punto de encuentro perfecto para locales y turistas que buscan experimentar la auténtica vida limeña.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.5
        ),
        Place(
            id = 4,
            name = "Museo Larco",
            description = "El Museo Larco es considerado uno de los museos arqueológicos más importantes de América Latina y una joya cultural del Perú. Ubicado en una hermosa casona virreinal del siglo XVIII, alberga una impresionante colección de más de 45,000 piezas arqueológicas que abarcan 5,000 años de historia peruana. Sus salas exhiben cerámicas, textiles, joyas y objetos de oro de las culturas Moche, Chimú, Nazca, Inca y otras civilizaciones precolombinas. El museo es mundialmente famoso por su galería de arte erótico precolombino, única en su tipo, y sus hermosos jardines coloniales que ofrecen un ambiente perfecto para la contemplación y el aprendizaje.",
            imageRes = R.drawable.map,
            location = "Pueblo Libre, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "9:00 AM - 10:00 PM",
            rating = 4.9
        ),
        Place(
            id = 5,
            name = "Circuito Mágico del Agua",
            description = "El Circuito Mágico del Agua es un espectacular complejo de fuentes ornamentales que ha sido reconocido por el Libro Guinness de los Récords como el complejo de fuentes más grande del mundo en un parque público. Este impresionante atractivo cuenta con 13 fuentes distintas que combinan agua, luz, música y tecnología de punta para crear un espectáculo visual único. Los visitantes pueden caminar entre las fuentes, disfrutar de shows de agua sincronizados con música clásica y peruana, y maravillarse con la Fuente de la Fantasía, la más grande del complejo. Es una experiencia mágica perfecta para toda la familia y uno de los atractivos más fotografiados de Lima.",
            imageRes = R.drawable.map,
            location = "Parque de la Reserva, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "3:00 PM - 10:30 PM",
            rating = 4.7
        ),
        Place(
            id = 6,
            name = "Huaca Pucllana",
            description = "La Huaca Pucllana es un impresionante sitio arqueológico de la cultura Lima que data del siglo V d.C., construido hace más de 1,500 años. Esta majestuosa pirámide de adobe de 25 metros de altura ofrece un contraste fascinante entre la Lima prehispánica y la moderna ciudad que la rodea. El sitio arqueológico incluye una pirámide trunca, plazas ceremoniales y un museo de sitio que exhibe cerámicas, textiles y objetos encontrados en las excavaciones. Las visitas nocturnas son especialmente espectaculares, ya que la huaca se ilumina creando un ambiente mágico que transporta a los visitantes a la época precolombina.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.6
        ),
        Place(
            id = 7,
            name = "Malecón de Miraflores",
            description = "El Malecón de Miraflores es uno de los paseos costeros más hermosos y románticos de Lima, extendiéndose por más de 6 kilómetros a lo largo de los acantilados con vistas espectaculares al Océano Pacífico. Este encantador paseo cuenta con senderos peatonales, ciclovías, parques temáticos, miradores estratégicos y áreas de entretenimiento. Es el lugar perfecto para caminar, correr, andar en bicicleta, hacer ejercicio al aire libre o simplemente disfrutar de los espectaculares atardeceres limeños. El malecón está rodeado de restaurantes, cafés, centros comerciales y el famoso Parque del Amor, convirtiéndose en el corazón social y recreativo de Miraflores.",
            imageRes = R.drawable.map,
            location = "Miraflores, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.8
        ),
        Place(
            id = 8,
            name = "Plaza San Martín",
            description = "La Plaza San Martín es una de las plazas más importantes y emblemáticas del centro histórico de Lima, declarada Patrimonio Cultural de la Nación. Esta majestuosa plaza está rodeada de impresionantes edificios coloniales y republicanos que representan diferentes épocas arquitectónicas de la ciudad. En el centro se alza el monumento ecuestre al Libertador José de San Martín, obra del escultor español Mariano Benlliure. La plaza es un punto de encuentro cultural y social donde convergen limeños y turistas, ofreciendo un ambiente histórico único con cafés tradicionales, librerías antiguas y la oportunidad de admirar la arquitectura que ha definido la identidad limeña a lo largo de los siglos.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "Abierto 24 horas",
            rating = 4.4
        ),
        Place(
            id = 9,
            name = "Museo de Arte de Lima (MALI)",
            description = "El Museo de Arte de Lima (MALI) es la institución cultural más importante del Perú y uno de los museos de arte más prestigiosos de América Latina. Ubicado en el hermoso Parque de la Exposición, el MALI alberga una colección excepcional de más de 17,000 obras de arte que abarcan desde la época precolombina hasta el arte contemporáneo peruano. Sus salas exhiben cerámicas prehispánicas, pinturas coloniales, arte republicano, fotografía histórica y obras de artistas contemporáneos. El museo cuenta con exposiciones temporales internacionales, programas educativos, talleres de arte y una biblioteca especializada, convirtiéndose en un centro cultural dinámico que promueve el arte y la cultura peruana.",
            imageRes = R.drawable.map,
            location = "Parque de la Exposición, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "10:00 AM - 7:00 PM",
            rating = 4.7
        ),
        Place(
            id = 10,
            name = "Barranco",
            description = "Barranco es el distrito bohemio y artístico más encantador de Lima, conocido como el 'Soho limeño'. Este pintoresco barrio costero combina la elegancia colonial con la modernidad artística, ofreciendo un ambiente único lleno de galerías de arte, cafés bohemios, bares de moda y restaurantes gourmet. El famoso Puente de los Suspiros es su símbolo más emblemático, rodeado de casonas coloniales coloridas y calles empedradas. Barranco es el corazón de la vida nocturna limeña, con bares de pisco, discotecas, música en vivo y una vibrante escena cultural. Es el lugar perfecto para artistas, escritores, músicos y todos aquellos que buscan inspiración y autenticidad.",
            imageRes = R.drawable.map,
            location = "Barranco, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "Abierto 24 horas",
            rating = 4.6
        ),
        Place(
            id = 11,
            name = "Casa de la Literatura Peruana",
            description = "La Casa de la Literatura Peruana es un centro cultural único ubicado en la hermosa antigua Estación de Desamparados, un edificio neoclásico del siglo XIX que ha sido restaurado para albergar la literatura peruana. Este espacio cultural ofrece exposiciones permanentes sobre la historia de la literatura peruana, desde la época prehispánica hasta la contemporánea, incluyendo manuscritos originales, primeras ediciones y objetos personales de grandes escritores como César Vallejo, Mario Vargas Llosa y José María Arguedas. El centro organiza talleres de escritura, conferencias, presentaciones de libros y eventos literarios, convirtiéndose en el epicentro de la cultura literaria limeña.",
            imageRes = R.drawable.map,
            location = "Centro Histórico, Lima",
            category = PlaceCategory.CULTURE,
            schedule = "10:00 AM - 7:00 PM",
            rating = 4.5
        ),
        Place(
            id = 12,
            name = "Parque de las Leyendas",
            description = "El Parque de las Leyendas es el zoológico más grande e importante del Perú y un parque temático único que recrea las diferentes regiones geográficas y culturas prehispánicas del país. Con más de 1,200 animales de 200 especies, el parque está dividido en zonas temáticas como la Costa, Sierra, Selva y Zona Internacional. Los visitantes pueden observar especies nativas como el cóndor andino, el oso de anteojos, monos, aves exóticas y reptiles, además de especies internacionales. El parque incluye un museo de sitio, jardines botánicos, áreas de recreación y espectáculos educativos, convirtiéndose en una experiencia perfecta para familias y amantes de la naturaleza.",
            imageRes = R.drawable.map,
            location = "San Miguel, Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.3
        ),
        Place(
            id = 13,
            name = "Convento de San Francisco",
            description = "El Convento de San Francisco es uno de los complejos religiosos coloniales más importantes de Lima y un tesoro arquitectónico del siglo XVI. Este majestuoso conjunto incluye la basílica, el convento y la biblioteca, destacando por su impresionante arquitectura barroca limeña. Las misteriosas catacumbas subterráneas, que albergan los restos de más de 25,000 personas, son uno de los atractivos más fascinantes. El convento cuenta con una biblioteca que contiene más de 25,000 volúmenes antiguos, incluyendo la primera Biblia impresa en América. Los claustros están decorados con azulejos sevillanos del siglo XVII y hermosos jardines coloniales, ofreciendo una experiencia única que combina historia, arte y espiritualidad.",
            imageRes = R.drawable.map,
            location = "Centro Histórico, Lima",
            category = PlaceCategory.HISTORICAL,
            schedule = "9:00 AM - 5:00 PM",
            rating = 4.7
        ),
        Place(
            id = 14,
            name = "Mercado Central de Lima",
            description = "El Mercado Central de Lima es el mercado más grande, tradicional y auténtico de la capital peruana, un verdadero tesoro gastronómico y cultural. Con más de 100 años de historia, este mercado bullicioso ofrece una experiencia sensorial única con más de 1,000 puestos que venden productos frescos, frutas exóticas, verduras, carnes, pescados, especias y hierbas aromáticas. Los visitantes pueden degustar comida típica peruana en sus comedores populares, probar jugos naturales, ceviches frescos y platos tradicionales. El mercado también alberga puestos de artesanías, textiles andinos, productos naturales y medicina tradicional, convirtiéndose en un microcosmos de la cultura peruana donde convergen tradición, sabor y autenticidad.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            category = PlaceCategory.TOURIST_ATTRACTION,
            schedule = "6:00 AM - 6:00 PM",
            rating = 4.2
        ),
        Place(
            id = 15,
            name = "Costa Verde",
            description = "La Costa Verde es el extenso malecón costero más hermoso de Lima, que se extiende por más de 20 kilómetros a lo largo de los acantilados del Océano Pacífico, conectando los distritos de Miraflores, Barranco, Chorrillos y otros. Este impresionante paseo costero ofrece senderos peatonales, ciclovías modernas, parques temáticos, miradores estratégicos y áreas de entretenimiento. Es el lugar perfecto para caminar, correr, andar en bicicleta, hacer ejercicio al aire libre, surfear o simplemente disfrutar de los espectaculares atardeceres limeños. La Costa Verde está rodeada de restaurantes, cafés, centros comerciales, playas y el famoso Parque del Amor, convirtiéndose en el corazón social, recreativo y deportivo de la ciudad.",
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

    //Lista de eventos en Lima
    val events = listOf(
        Event(
            id = 1,
            name = "Juegos Panamericanos Lima 2019",
            description = "Los Juegos Panamericanos Lima 2019 fueron el evento deportivo más importante de América, reuniendo a más de 6,700 atletas de 41 países en 39 deportes y 61 disciplinas. Este prestigioso evento multideportivo se celebró en el moderno Estadio Nacional y otros 14 recintos deportivos de clase mundial. Los juegos incluyeron competencias de atletismo, natación, gimnasia, fútbol, básquet, voleibol y deportes acuáticos, entre otros. Lima se convirtió en la capital deportiva del continente, ofreciendo una experiencia única con ceremonias de apertura y clausura espectaculares, y la oportunidad de ver a los mejores atletas panamericanos en acción.",
            imageRes = R.drawable.map,
            location = "Estadio Nacional, Lima",
            date = "26/07/2019",
            schedule = "9:00 AM - 10:00 PM",
            category = EventCategory.SPORTS,
            price = 50.0
        ),
        Event(
            id = 2,
            name = "Festival Gastronómico Mistura",
            description = "Mistura es la feria gastronómica más importante de América Latina y uno de los eventos culinarios más prestigiosos del mundo. Este festival celebra la rica diversidad gastronómica del Perú, reuniendo a los mejores chefs nacionales e internacionales, productores locales, cocineros tradicionales y amantes de la comida peruana. Durante una semana, los visitantes pueden degustar platos auténticos de todas las regiones del Perú, desde el ceviche costeño hasta los sabores andinos, participar en talleres de cocina, conferencias gastronómicas y disfrutar de espectáculos culturales. Mistura es una experiencia sensorial única que pone en valor la tradición culinaria peruana y su reconocimiento mundial.",
            imageRes = R.drawable.map,
            location = "Costa Verde, Magdalena",
            date = "15/09/2024",
            schedule = "11:00 AM - 9:00 PM",
            category = EventCategory.FESTIVAL,
            price = 30.0
        ),
        Event(
            id = 3,
            name = "Concierto de Gianmarco",
            description = "Gianmarco, el cantautor peruano más reconocido internacionalmente, presenta su nuevo álbum en un concierto íntimo y emotivo en el Arena Perú. Con más de 20 años de carrera y múltiples premios Grammy Latinos, Gianmarco ofrecerá una noche inolvidable interpretando sus mayores éxitos como 'Se me olvidó', 'Te amo' y 'No me toques', junto con las nuevas canciones de su último trabajo discográfico. El concierto incluirá una orquesta en vivo, efectos visuales espectaculares y momentos de interacción con el público. Es una oportunidad única para disfrutar de la música romántica y el talento de uno de los artistas más queridos del Perú.",
            imageRes = R.drawable.map,
            location = "Arena Perú, Surco",
            date = "20/11/2024",
            schedule = "8:00 PM - 11:00 PM",
            category = EventCategory.CONCERT,
            price = 120.0
        ),
        Event(
            id = 4,
            name = "Feria del Libro Ricardo Palma",
            description = "La feria del libro más importante del Perú. Presentaciones de autores, venta de libros, conferencias y actividades culturales para toda la familia.",
            imageRes = R.drawable.map,
            location = "Parque Próceres, Jesús María",
            date = "25/10/2024",
            schedule = "10:00 AM - 9:00 PM",
            category = EventCategory.EXHIBITION,
            price = 0.0
        ),
        Event(
            id = 5,
            name = "Festival de Cine de Lima",
            description = "El Festival de Cine de Lima es el evento cinematográfico más importante del Perú y uno de los festivales de cine más prestigiosos de América Latina. Durante una semana, el festival presenta más de 200 películas de 40 países, incluyendo estrenos mundiales, películas peruanas, documentales, cortometrajes y obras maestras del cine internacional. El evento incluye proyecciones en múltiples salas, talleres de dirección y actuación, encuentros con directores y actores reconocidos, conferencias magistrales y competencias de cine independiente. Es una oportunidad única para cineastas, críticos y amantes del séptimo arte de descubrir nuevas tendencias cinematográficas y conectar con la industria del cine.",
            imageRes = R.drawable.map,
            location = "Centro de Lima",
            date = "15/08/2024",
            schedule = "2:00 PM - 10:00 PM",
            category = EventCategory.FESTIVAL,
            price = 25.0
        ),
        Event(
            id = 6,
            name = "Maratón de Lima",
            description = "La Maratón de Lima es el evento deportivo más masivo e importante del Perú, que reúne a más de 15,000 corredores de todas las edades y niveles. Esta carrera atlética de 42 kilómetros recorre los principales distritos y avenidas de Lima, ofreciendo vistas espectaculares de la ciudad mientras los participantes corren por el centro histórico, la costa verde y los barrios más emblemáticos. El evento incluye categorías para maratón completo, media maratón, 10K y 5K, con premios para los ganadores y medallas para todos los participantes. La maratón cuenta con estaciones de hidratación, apoyo médico, música en vivo y el apoyo de miles de espectadores que animan a los corredores, creando una atmósfera festiva y motivadora.",
            imageRes = R.drawable.map,
            location = "Varios distritos, Lima",
            date = "12/05/2024",
            schedule = "6:00 AM - 12:00 PM",
            category = EventCategory.SPORTS,
            price = 50.0
        ),
        Event(
            id = 7,
            name = "Festival de Jazz de Lima",
            description = "El Festival de Jazz de Lima es el encuentro musical más importante del género en el Perú, reuniendo a los mejores músicos de jazz nacionales e internacionales en el bohemio distrito de Barranco. Durante tres días, el festival presenta conciertos íntimos en bares y salas de concierto, jam sessions espontáneas en las calles, talleres de improvisación musical y masterclasses con músicos reconocidos. El evento incluye géneros como jazz tradicional, fusion, latin jazz y jazz experimental, ofreciendo una experiencia musical única que combina la tradición jazzística internacional con la creatividad peruana. Es el lugar perfecto para melómanos, músicos y amantes de la música en vivo.",
            imageRes = R.drawable.map,
            location = "Barranco, Lima",
            date = "20/09/2024",
            schedule = "7:00 PM - 11:00 PM",
            category = EventCategory.CONCERT,
            price = 80.0
        ),
        Event(
            id = 8,
            name = "Exposición de Arte Contemporáneo",
            description = "Muestra de arte contemporáneo peruano e internacional. Obras de pintura, escultura, fotografía y arte digital.",
            imageRes = R.drawable.map,
            location = "Museo de Arte de Lima",
            date = "01/11/2024",
            schedule = "10:00 AM - 8:00 PM",
            category = EventCategory.EXHIBITION,
            price = 15.0
        ),
        Event(
            id = 9,
            name = "Festival de Danza Folklórica",
            description = "Celebración de las danzas tradicionales del Perú. Presentaciones de grupos folklóricos de diferentes regiones del país.",
            imageRes = R.drawable.map,
            location = "Plaza San Martín",
            date = "28/07/2024",
            schedule = "6:00 PM - 9:00 PM",
            category = EventCategory.FESTIVAL,
            price = 0.0
        ),
        Event(
            id = 10,
            name = "Conferencia de Tecnología e Innovación",
            description = "Evento que reúne a expertos en tecnología, emprendimiento e innovación. Charlas magistrales, networking y exposición de startups.",
            imageRes = R.drawable.map,
            location = "Centro de Convenciones, San Isidro",
            date = "15/06/2024",
            schedule = "9:00 AM - 6:00 PM",
            category = EventCategory.CONFERENCE,
            price = 120.0
        ),
        Event(
            id = 11,
            name = "Festival de Cerveza Artesanal",
            description = "Celebración de la cultura cervecera peruana. Degustación de cervezas artesanales, food trucks y música en vivo.",
            imageRes = R.drawable.map,
            location = "Parque Kennedy, Miraflores",
            date = "10/08/2024",
            schedule = "12:00 PM - 8:00 PM",
            category = EventCategory.FESTIVAL,
            price = 30.0
        ),
        Event(
            id = 12,
            name = "Torneo de Surf en Costa Verde",
            description = "Competencia de surf en las playas de Lima. Atletas nacionales e internacionales compiten en diferentes categorías.",
            imageRes = R.drawable.map,
            location = "Costa Verde, Miraflores",
            date = "05/04/2024",
            schedule = "7:00 AM - 5:00 PM",
            category = EventCategory.SPORTS,
            price = 0.0
        )
    )

    // Lista para servicios disponibles
    val services = listOf(
        Service(
            id = 1,
            name = "Central Restaurante",
            description = "Central es el restaurante más prestigioso del Perú y uno de los mejores de América Latina, dirigido por el reconocido chef Virgilio Martínez. Ubicado en una hermosa casona de Barranco, Central ofrece una experiencia culinaria única basada en la biodiversidad peruana, utilizando ingredientes de los diferentes ecosistemas del país, desde el Pacífico hasta los Andes y la Amazonía. El restaurante ha sido galardonado con múltiples premios internacionales y aparece en las listas de los mejores restaurantes del mundo. Su menú degustación de 17 platos es un viaje gastronómico por la geografía peruana, combinando técnicas modernas con tradiciones ancestrales.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 242 8515",
            location = "Barranco, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$$$",
            schedule = "12:45 PM - 3:00 PM, 7:45 PM - 10:00 PM"
        ),
        Service(
            id = 2,
            name = "Maido Restaurante",
            description = "Maido es el templo de la cocina nikkei en Lima, dirigido por el chef Mitsuharu Tsumura, uno de los chefs más reconocidos del Perú. Este restaurante de alta cocina combina magistralmente ingredientes peruanos con técnicas culinarias japonesas, creando una fusión única que ha conquistado paladares internacionales. Maido ha sido reconocido como uno de los mejores restaurantes de América Latina y ofrece una experiencia gastronómica sofisticada con platos como el tiradito nikkei, el ceviche con toques japoneses y postres que fusionan ambas culturas. El ambiente elegante y la atención al detalle hacen de Maido una experiencia culinaria inolvidable.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 446 2512",
            location = "Miraflores, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$$$",
            schedule = "1:00 PM - 3:00 PM, 7:30 PM - 11:00 PM"
        ),
        Service(
            id = 3,
            name = "Tío Mario Anticuchos",
            description = "Tío Mario es una institución culinaria en Lima, especializada en anticuchos tradicionales peruanos con más de 30 años de tradición familiar. Este emblemático local de comida callejera auténtica ha mantenido la receta original de anticuchos de corazón de res marinados en ají panca, vinagre y especias, asados a la parrilla con maestría. El ambiente familiar y la atención personalizada de Mario y su familia hacen que cada visita sea una experiencia única. Los anticuchos se sirven con papa sancochada, choclo y ají criollo, acompañados de su famosa salsa de ají. Es el lugar perfecto para experimentar la auténtica comida callejera limeña en un ambiente seguro y tradicional.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 999 888 777",
            location = "Jesús María, Lima",
            category = GastronomyCategory.STREET_FOOD,
            priceRange = "$",
            schedule = "6:00 PM - 12:00 AM"
        ),
        Service(
            id = 4,
            name = "Metropolitano",
            description = "Sistema de transporte rápido en buses articulados. Conecta el norte y sur de Lima con carriles exclusivos. Es la forma más rápida y segura de moverse por la ciudad.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "www.metropolitano.gob.pe",
            location = "Varias estaciones, Lima",
            category = TransportCategory.BUS,
            priceRange = "$",
            schedule = "5:00 AM - 10:00 PM"
        ),
        Service(
            id = 5,
            name = "Lima Airport Partners - Taxi",
            description = "Servicio oficial de taxis del Aeropuerto Jorge Chávez. Tarifas fijas, seguros y confiables para trasladarte desde el aeropuerto a cualquier punto de Lima.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "+51 1 517 3100",
            location = "Aeropuerto Jorge Chávez",
            category = TransportCategory.TAXI,
            priceRange = "$$",
            schedule = "24 horas"
        ),
        Service(
            id = 6,
            name = "Café del Museo",
            description = "Cafetería ubicada en el Museo Larco. Ofrece café peruano de especialidad, postres artesanales y un ambiente rodeado de jardines coloniales.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 462 4757",
            location = "Pueblo Libre, Lima",
            category = GastronomyCategory.CAFE,
            priceRange = "$$",
            schedule = "9:00 AM - 10:00 PM"
        ),
        Service(
            id = 7,
            name = "Astrid y Gastón",
            description = "Astrid y Gastón es el restaurante emblemático de la alta cocina peruana, dirigido por el reconocido chef Gastón Acurio, uno de los embajadores de la gastronomía peruana en el mundo. Ubicado en una hermosa casona de San Isidro, el restaurante ofrece una experiencia culinaria única que combina ingredientes locales de las tres regiones del Perú con técnicas culinarias innovadoras y presentaciones artísticas. El menú degustación incluye platos como el cuy confitado, la quinua con mariscos y postres que fusionan tradición y modernidad. El restaurante ha sido reconocido internacionalmente y es considerado una institución de la gastronomía peruana, ofreciendo una experiencia gastronómica inolvidable en un ambiente elegante y sofisticado.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 442 2777",
            location = "San Isidro, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$$$$",
            schedule = "12:30 PM - 3:30 PM, 7:30 PM - 11:30 PM"
        ),
        Service(
            id = 8,
            name = "La Mar Cebichería",
            description = "La Mar Cebichería es la cebichería más prestigiosa de Lima y una institución de la cocina marina peruana, especializada en ceviche y mariscos frescos del Pacífico. Con un ambiente casual y moderno, el restaurante ofrece la mejor selección de pescados del día, preparados con técnicas tradicionales y presentaciones contemporáneas. Sus especialidades incluyen ceviches clásicos, tiraditos innovadores, causas de mariscos y platos de pescado a la parrilla. La Mar cuenta con una carta de piscos premium y cócteles marinos únicos. El restaurante ha sido reconocido internacionalmente por su calidad y autenticidad, convirtiéndose en un destino obligatorio para los amantes de la cocina marina peruana.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 421 3365",
            location = "Miraflores, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$$$",
            schedule = "12:00 PM - 4:00 PM, 7:00 PM - 11:00 PM"
        ),
        Service(
            id = 9,
            name = "El Pan de la Chola",
            description = "El Pan de la Chola es una panadería artesanal única en Lima, especializada en panes de masa madre elaborados con técnicas tradicionales y ingredientes naturales. Esta encantadora panadería ofrece una variedad excepcional de panes artesanales, desde baguettes crujientes hasta panes integrales, sourdough y panes con semillas. Además de sus panes, la panadería cuenta con una selección de pasteles caseros, croissants, muffins y postres artesanales. El café de especialidad se prepara con granos peruanos de alta calidad, ofreciendo una experiencia gastronómica completa. El ambiente rústico y acogedor, con mesas de madera y decoración vintage, hace que cada visita sea una experiencia única para los amantes del pan artesanal y el café de calidad.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 221 2130",
            location = "Miraflores, Lima",
            category = GastronomyCategory.CAFE,
            priceRange = "$$",
            schedule = "7:00 AM - 8:00 PM"
        ),
        Service(
            id = 10,
            name = "Isolina Taberna Peruana",
            description = "Taberna tradicional que sirve comida casera peruana. Ambiente familiar con platos típicos y bebidas tradicionales.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 247 5075",
            location = "Barranco, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$$",
            schedule = "12:00 PM - 11:00 PM"
        ),
        Service(
            id = 11,
            name = "Corredor Verde - Bicicletas",
            description = "Servicio de alquiler de bicicletas para recorrer la Costa Verde. Incluye bicicletas eléctricas y tradicionales con guías turísticos.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "+51 999 123 456",
            location = "Costa Verde, Miraflores",
            category = TransportCategory.RENTAL,
            priceRange = "$$",
            schedule = "6:00 AM - 10:00 PM"
        ),
        Service(
            id = 12,
            name = "Uber Lima",
            description = "Servicio de transporte privado a través de aplicación móvil. Vehículos cómodos y seguros con conductores verificados.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "App móvil",
            location = "Toda Lima",
            category = TransportCategory.TAXI,
            priceRange = "$$",
            schedule = "24 horas"
        ),
        Service(
            id = 13,
            name = "Corredor Morado - BRT",
            description = "Sistema de buses rápidos que conecta el norte y sur de Lima. Transporte público eficiente con carriles exclusivos.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "www.corredormorado.gob.pe",
            location = "Norte-Sur de Lima",
            category = TransportCategory.BUS,
            priceRange = "$",
            schedule = "5:00 AM - 11:00 PM"
        ),
        Service(
            id = 14,
            name = "Lima Tours - Agencia",
            description = "Agencia de turismo especializada en tours por Lima y alrededores. Tours guiados, transporte privado y paquetes turísticos.",
            imageRes = R.drawable.map,
            type = ServiceType.TOUR,
            contact = "+51 1 234 5678",
            location = "Centro de Lima",
            category = "Tours",
            priceRange = "$$$",
            schedule = "8:00 AM - 6:00 PM"
        ),
        Service(
            id = 15,
            name = "Pollería Don Tito",
            description = "Especialistas en pollo a la brasa peruano. Pollería tradicional con más de 20 años de experiencia y sabor auténtico.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 456 7890",
            location = "Miraflores, Lima",
            category = GastronomyCategory.RESTAURANT,
            priceRange = "$",
            schedule = "11:00 AM - 11:00 PM"
        ),
        Service(
            id = 16,
            name = "Tren Eléctrico de Lima",
            description = "Sistema de tren eléctrico que conecta el centro con el sur de Lima. Transporte moderno, rápido y ecológico.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "www.trenlima.gob.pe",
            location = "Centro-Sur de Lima",
            category = TransportCategory.TRAIN,
            priceRange = "$",
            schedule = "5:30 AM - 11:30 PM"
        ),
        Service(
            id = 17,
            name = "Bar Huaringas",
            description = "Bar especializado en pisco y cocteles peruanos. Ambiente bohemio con música en vivo y la mejor selección de piscos del país.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 789 0123",
            location = "Barranco, Lima",
            category = GastronomyCategory.BAR,
            priceRange = "$$",
            schedule = "6:00 PM - 2:00 AM"
        ),
        Service(
            id = 18,
            name = "Delivery Rappi",
            description = "Servicio de delivery de comida y productos. Entrega rápida a domicilio con una amplia variedad de restaurantes y tiendas.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "App móvil",
            location = "Toda Lima",
            category = GastronomyCategory.STREET_FOOD,
            priceRange = "$$",
            schedule = "24 horas"
        ),
        Service(
            id = 19,
            name = "Taxi Seguro Lima",
            description = "Servicio de taxis seguros y confiables. Vehículos modernos con GPS y conductores capacitados para turistas.",
            imageRes = R.drawable.map,
            type = ServiceType.TRANSPORT,
            contact = "+51 1 555 0123",
            location = "Toda Lima",
            category = TransportCategory.TAXI,
            priceRange = "$$",
            schedule = "24 horas"
        ),
        Service(
            id = 20,
            name = "Café Haiti",
            description = "Café tradicional limeño con más de 50 años de historia. Especialistas en café peruano y ambiente bohemio del centro histórico.",
            imageRes = R.drawable.map,
            type = ServiceType.GASTRONOMY,
            contact = "+51 1 428 1464",
            location = "Centro Histórico, Lima",
            category = GastronomyCategory.CAFE,
            priceRange = "$",
            schedule = "7:00 AM - 10:00 PM"
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