package com.tecsup.travelmarket.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tecsup.travelmarket.data.Repository
import com.tecsup.travelmarket.data.SearchResult
import com.tecsup.travelmarket.model.*
import com.tecsup.travelmarket.ui.theme.*

/**
 * Componente de barra de búsqueda con resultados en tiempo real
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onItemClick: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    var showResults by remember { mutableStateOf(false) }
    val repository = remember { Repository() }
    
    // Búsqueda reactiva - se ejecuta cuando cambia el query
    val searchResults by remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isNotBlank()) {
                repository.searchItems(searchQuery)
            } else {
                SearchResult(emptyList(), emptyList(), emptyList())
            }
        }
    }
    
    // Mostrar resultados cuando hay query y resultados
    LaunchedEffect(searchQuery) {
        showResults = searchQuery.isNotBlank() && searchResults.hasResults
    }
    
    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                onSearchQueryChange(it)
                showResults = it.isNotBlank()
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp)),
            placeholder = {
                Text(
                    "Buscar lugares, eventos o servicios...",
                    color = TextLight,
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = TextLight
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { 
                            onSearchQueryChange("")
                            showResults = false
                        }
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Limpiar",
                            tint = TextLight
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = if (showResults) TurquoisePrimary else Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )
        
        // Mostrar resultados de búsqueda en tiempo real
        if (showResults && searchQuery.isNotBlank()) {
            SearchResultsDropdown(
                searchResults = searchResults,
                onItemClick = { item ->
                    onItemClick(item)
                    onSearchQueryChange("")
                    showResults = false
                },
                onDismiss = {
                    showResults = false
                }
            )
        }
    }
}

/**
 * Dropdown con resultados de búsqueda
 */
@Composable
fun SearchResultsDropdown(
    searchResults: SearchResult,
    onItemClick: (Any) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Lugares
            if (searchResults.places.isNotEmpty()) {
                SearchSection(
                    title = "Lugares (${searchResults.places.size})",
                    items = searchResults.places,
                    onItemClick = onItemClick
                )
            }
            
            // Eventos
            if (searchResults.events.isNotEmpty()) {
                SearchSection(
                    title = "Eventos (${searchResults.events.size})",
                    items = searchResults.events,
                    onItemClick = onItemClick
                )
            }
            
            // Servicios
            if (searchResults.services.isNotEmpty()) {
                SearchSection(
                    title = "Servicios (${searchResults.services.size})",
                    items = searchResults.services,
                    onItemClick = onItemClick
                )
            }
            
            // Si no hay resultados
            if (!searchResults.hasResults) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.SearchOff,
                        contentDescription = "Sin resultados",
                        tint = TextLight,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "No se encontraron resultados para \"${searchResults.places.firstOrNull()?.let { "" } ?: ""}\"",
                        color = TextLight,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

/**
 * Sección de resultados de búsqueda
 */
@Composable
fun SearchSection(
    title: String,
    items: List<Any>,
    onItemClick: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
        
        items.take(3).forEach { item ->
            SearchResultItem(
                item = item,
                onClick = { onItemClick(item) }
            )
        }
        
        if (items.size > 3) {
            Text(
                text = "y ${items.size - 3} más...",
                fontSize = 12.sp,
                color = TextLight,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

/**
 * Item individual de resultado de búsqueda
 */
@Composable
fun SearchResultItem(
    item: Any,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (name, description, category) = when (item) {
        is Place -> Triple(item.name, item.description, item.category)
        is Event -> Triple(item.name, item.description, item.category)
        is Service -> Triple(item.name, item.description, item.type)
        else -> Triple("", "", "")
    }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = when (item) {
                is Place -> Icons.Default.Place
                is Event -> Icons.Default.Event
                is Service -> Icons.Default.Business
                else -> Icons.Default.Info
            },
            contentDescription = name,
            tint = TurquoisePrimary,
            modifier = Modifier.size(20.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                maxLines = 1
            )
            Text(
                text = description.take(50) + if (description.length > 50) "..." else "",
                fontSize = 12.sp,
                color = TextSecondary,
                maxLines = 1
            )
            Text(
                text = category,
                fontSize = 10.sp,
                color = TextLight
            )
        }
    }
}
