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

        composable(
            route = "${Screen.Detail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailScreen(placeId = id, navController = navController)
        }

    }
}