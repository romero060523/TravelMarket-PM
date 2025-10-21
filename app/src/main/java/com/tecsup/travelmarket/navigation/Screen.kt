package com.tecsup.travelmarket.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    //Pantalla de autenticion
    object Login : Screen("login")
    object Register : Screen("register")

    // Barra de navegacion
    object Home : Screen("home", "Inicio", Icons.Filled.Home)
    object Favorites : Screen("favorite", "Favoritos", Icons.Filled.Favorite)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person)

    // Pantalla de detalle
    object Detail : Screen("detail")

    // Pantalla para editar perfil
    object EditProfile : Screen("edit_profile")
    
    // Pantallas de categorías
    object Places : Screen("places", "Lugares", Icons.Filled.Place)
    object Events : Screen("events", "Eventos", Icons.Filled.Event)
    object Gastronomy : Screen("gastronomy", "Gastronomía", Icons.Filled.Restaurant)
    object Transport : Screen("transport", "Transporte", Icons.Filled.DirectionsBus)
}