package com.tecsup.travelmarket.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tecsup.travelmarket.data.TravelViewModel
import com.tecsup.travelmarket.ui.screens.DetailScreen
import com.tecsup.travelmarket.ui.screens.EditProfileScreen
import com.tecsup.travelmarket.ui.screens.HomeScreen
import com.tecsup.travelmarket.ui.screens.FavoriteScreen
import com.tecsup.travelmarket.ui.screens.ProfileScreen
import com.tecsup.travelmarket.ui.screens.PlacesScreen
import com.tecsup.travelmarket.ui.screens.EventsScreen
import com.tecsup.travelmarket.ui.screens.GastronomyScreen
import com.tecsup.travelmarket.ui.screens.LoginScreen
import com.tecsup.travelmarket.ui.screens.RegisterScreen
import com.tecsup.travelmarket.ui.screens.TransportScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: TravelViewModel,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        //Pantallas de autenticacion
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }

        composable(Screen.Home.route) { HomeScreen(navController, viewModel) }
        composable(Screen.Favorites.route) { FavoriteScreen(navController, viewModel) }

        // Pantalla de perfil
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.EditProfile.route) { EditProfileScreen(navController) }
        
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
            DetailScreen(placeId = id, navController = navController, type = type, viewModel)
        }

    }
}