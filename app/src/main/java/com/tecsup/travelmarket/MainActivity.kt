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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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
                val items = listOf(
                    Screen.Home,
                    Screen.Favorites,
                    Screen.Profile
                )

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = items,
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
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