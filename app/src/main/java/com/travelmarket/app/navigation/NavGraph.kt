package com.travelmarket.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.travelmarket.app.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            SimpleHomeScreen(navController = navController)
        }
        
        composable(Screen.Places.route) {
            PlacesScreen(navController = navController)
        }
        
        composable(Screen.Events.route) {
            EventsScreen(navController = navController)
        }
        
        composable(Screen.Services.route) {
            ServicesScreen(navController = navController)
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        
        composable(Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId") ?: ""
            PlaceDetailScreen(
                placeId = placeId,
                navController = navController
            )
        }
        
        composable(Screen.EventDetail.route) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: ""
            EventDetailScreen(
                eventId = eventId,
                navController = navController
            )
        }
        
        composable(Screen.ServiceDetail.route) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            ServiceDetailScreen(
                serviceId = serviceId,
                navController = navController
            )
        }
    }
}
