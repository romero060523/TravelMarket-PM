package com.tecsup.travelmarket.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Inicio", Icons.Filled.Home)
    object Favorites : Screen("favorite", "Favoritos", Icons.Filled.Favorite)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person)
}