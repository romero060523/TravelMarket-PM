package com.tecsup.travelmarket.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tecsup.travelmarket.navigation.Screen

@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    navController: NavController
) {
    NavigationBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { screen.icon?.let { Icon(it, contentDescription = screen.title) } },
                label = { screen.title?.let { Text(it) } },
                selected = currentDestination == screen.route,
                onClick = {
                    if (currentDestination != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF007AFF), // Azul activo
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF007AFF),
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
