package com.tecsup.travelmarket.ui.screens

import com.tecsup.travelmarket.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.components.ItemCard


data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun HomeScreen(navController: NavController) {
    val places = listOf(
        Place(1, "Estadio Nacional", "Sede principal de los Juegos Panamericanos.", R.drawable.map),
        Place(2, "Parque Kennedy", "Famoso por su gastronomía y vida nocturna.", R.drawable.map),
        Place(3, "Museo Larco", "Colección arqueológica precolombina destacada.", R.drawable.map)
    )

    LazyColumn {
        items(places) { place ->
            ItemCard(
                name = place.name,
                description = place.description,
                imageRes = place.imageRes,
                onClick = {
                    navController.navigate("${Screen.Detail.route}/${place.id}")
                }
            )
        }
    }
}