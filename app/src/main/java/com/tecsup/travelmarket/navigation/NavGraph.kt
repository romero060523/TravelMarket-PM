package com.tecsup.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tecsup.travelmarket.ui.screens.HomeScreen
import com.tecsup.travelmarket.ui.screens.FavoriteScreen
import com.tecsup.travelmarket.ui.screens.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Favorites.route) { FavoriteScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}