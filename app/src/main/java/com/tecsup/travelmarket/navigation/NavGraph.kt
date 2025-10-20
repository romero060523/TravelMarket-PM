package com.tecsup.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tecsup.travelmarket.ui.screens.DetailScreen
import com.tecsup.travelmarket.ui.screens.HomeScreen
import com.tecsup.travelmarket.ui.screens.FavoriteScreen
import com.tecsup.travelmarket.ui.screens.ProfileScreen
import com.tecsup.travelmarket.ui.screens.PlacesScreen
import com.tecsup.travelmarket.ui.screens.EventsScreen
import com.tecsup.travelmarket.ui.screens.GastronomyScreen
import com.tecsup.travelmarket.ui.screens.TransportScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Favorites.route) { FavoriteScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        
        // Pantallas de categorías
        composable(Screen.Places.route) { PlacesScreen(navController) }
        composable(Screen.Events.route) { EventsScreen(navController) }
        composable(Screen.Gastronomy.route) { GastronomyScreen(navController) }
        composable(Screen.Transport.route) { TransportScreen(navController) }

        composable(
            route = "${Screen.Detail.route}/{type}/{id}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val type = backStackEntry.arguments?.getString("type")
            DetailScreen(placeId = id, navController = navController, type = type)
        }

    }
}