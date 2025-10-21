package com.tecsup.travelmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tecsup.travelmarket.data.AuthRepositoryProvider
import com.tecsup.travelmarket.navigation.NavGraph
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.BottomNavigationBar
import com.tecsup.travelmarket.ui.theme.TravelMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelMarketTheme {
                val navController = rememberNavController()
                val authRepository = remember { AuthRepositoryProvider.authRepository }

                // Observar la ruta actual
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Definir rutas que deben mostrar el BottomNav
                val routesWithBottomNav = listOf(
                    Screen.Home.route,
                    Screen.Favorites.route,
                    Screen.Profile.route
                )

                // Items del BottomNav
                val bottomNavItems = listOf(
                    Screen.Home,
                    Screen.Favorites,
                    Screen.Profile
                )

                // Mostrar BottomNav solo si estamos en las rutas principales
                val showBottomNav = currentRoute in routesWithBottomNav

                Scaffold(
                    bottomBar = {
                        if (showBottomNav) {
                            BottomNavigationBar(
                                items = bottomNavItems,
                                navController = navController
                            )
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = if (authRepository.isLoggedIn()) {
                            Screen.Home.route
                        } else {
                            Screen.Login.route
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelMarketTheme {
        Greeting("Android")
    }
}