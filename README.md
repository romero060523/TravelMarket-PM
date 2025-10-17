# 🧳 TravelMarket

Una aplicación móvil desarrollada en **Android** con **Kotlin** y **Jetpack Compose** para el mercado de viajes y turismo.

## 📱 Descripción

TravelMarket es una aplicación que permite a los usuarios explorar destinos turísticos, eventos, gastronomía local y servicios de transporte. La aplicación está diseñada con una arquitectura moderna y escalable.

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura en capas** organizada de la siguiente manera:

```
com/tecsup/travelmarket/
├── 🎨 ui/                    # Capa de presentación
│   ├── theme/               # Temas y estilos
│   ├── components/          # Componentes reutilizables
│   └── screens/             # Pantallas de la aplicación
├── 📊 model/                # Modelos de datos
├── 💾 data/                 # Capa de datos
│   ├── Repository.kt        # Repositorio principal
│   ├── LocalData.kt         # Datos locales
│   ├── RemoteData.kt        # Datos remotos
│   └── TravelViewModel.kt   # ViewModel
├── 🧭 navigation/           # Navegación
└── 🔧 utils/                # Utilidades
```

## 🛠️ Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - Framework de UI moderno
- **Material 3** - Sistema de diseño
- **Navigation Compose** - Navegación entre pantallas
- **ViewModel** - Gestión de estado
- **Repository Pattern** - Patrón de repositorio
- **JSON** - Almacenamiento de datos locales

## 📱 Pantallas Principales

- **🏠 Home** - Pantalla principal con destinos destacados
- **📍 Places** - Explorar lugares turísticos
- **🎉 Events** - Eventos y actividades
- **🍽️ Gastronomy** - Gastronomía local
- **🚌 Transport** - Servicios de transporte
- **👤 Profile** - Perfil del usuario
- **📄 Detail** - Detalles de lugares/eventos

## 🎨 Características de UI

- **Material 3** con esquemas de colores dinámicos
- **Modo oscuro** automático
- **Componentes reutilizables**:
  - `ItemCard` - Tarjetas de elementos
  - `SearchBar` - Barra de búsqueda
  - `ErrorView` - Vista de errores
- **Navegación fluida** entre pantallas

## 📦 Estructura del Proyecto

### 🎨 Capa de UI
```
ui/
├── theme/
│   ├── Color.kt          # Colores del tema
│   ├── Theme.kt          # Configuración del tema
│   └── Type.kt           # Tipografía
├── components/
│   ├── ItemCard.kt       # Componente de tarjeta
│   ├── SearchBar.kt      # Barra de búsqueda
│   └── ErrorView.kt      # Vista de errores
└── screens/
    ├── HomeScreen.kt     # Pantalla principal
    ├── DetailScreen.kt   # Pantalla de detalles
    ├── ProfileScreen.kt  # Perfil de usuario
    ├── PlacesScreen.kt   # Lugares turísticos
    ├── EventsScreen.kt   # Eventos
    ├── GastronomyScreen.kt # Gastronomía
    └── TransportScreen.kt # Transporte
```

### 📊 Modelos de Datos
```
model/
├── Place.kt              # Modelo de lugar
├── Event.kt              # Modelo de evento
├── Service.kt            # Modelo de servicio
└── User.kt               # Modelo de usuario
```

### 💾 Capa de Datos
```
data/
├── Repository.kt         # Repositorio principal
├── LocalData.kt          # Fuente de datos local
├── RemoteData.kt         # Fuente de datos remota
└── TravelViewModel.kt    # ViewModel principal
```

### 🧭 Navegación
```
navigation/
├── NavGraph.kt           # Grafo de navegación
└── Screen.kt             # Definición de pantallas
```

### 🔧 Utilidades
```
utils/
├── Constants.kt          # Constantes de la aplicación
└── Extensions.kt         # Extensiones de Kotlin
```

## 🚀 Instalación y Configuración

### Prerrequisitos
- **Android Studio** (versión más reciente)
- **JDK 11** o superior
- **Android SDK** (API 24+)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd TravelMarket
   ```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar "Open an existing project"
   - Navegar a la carpeta del proyecto

3. **Sincronizar el proyecto**
   - Android Studio sincronizará automáticamente las dependencias
   - Esperar a que termine la sincronización

4. **Ejecutar la aplicación**
   - Conectar dispositivo Android o iniciar emulador
   - Hacer clic en "Run" (▶️) o presionar `Shift + F10`

## 📱 Funcionalidades

### ✅ Implementadas
- [x] Estructura base del proyecto
- [x] Tema Material 3
- [x] Navegación entre pantallas
- [x] Arquitectura MVVM
- [x] Componentes reutilizables

### 🔄 En Desarrollo
- [ ] Implementación de pantallas
- [ ] Integración de datos JSON
- [ ] Funcionalidad de búsqueda
- [ ] Sistema de favoritos
- [ ] Manejo de errores

### 📋 Futuras Funcionalidades
- [ ] Autenticación de usuarios
- [ ] Base de datos en la nube
- [ ] Notificaciones push
- [ ] Mapas integrados
- [ ] Sistema de reseñas
- [ ] Reservas en línea

## 🎯 Características Técnicas

- **Arquitectura**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose
- **Navegación**: Navigation Compose
- **Diseño**: Material 3
- **Lenguaje**: Kotlin
- **Mínimo SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Contribuidores

- **Daniel Alexander** - Desarrollador Principal

## 📞 Contacto

Para preguntas o sugerencias sobre el proyecto, puedes contactar a través de:
- **Email**: [tu-email@ejemplo.com]
- **GitHub**: [tu-usuario-github]

---

**Desarrollado con ❤️ para la comunidad de viajeros**
