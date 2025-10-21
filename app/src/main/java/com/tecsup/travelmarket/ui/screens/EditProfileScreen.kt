package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.data.AuthRepositoryProvider
import com.tecsup.travelmarket.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    val authRepository = remember { AuthRepositoryProvider.authRepository }
    val user = authRepository.getCurrentUser()
    val keyboardController = LocalSoftwareKeyboardController.current

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf(user?.name ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var isLoading by remember { mutableStateOf(false) }
    var showSuccessSnackbar by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundWhite)
        ) {
            // Header con fondo turquesa
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = TurquoisePrimary,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Editar Perfil",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Contenido con scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundGray)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Card con formulario
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar con iniciales y botón de cámara
                        Box(
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = TurquoisePrimary,
                                modifier = Modifier.size(100.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = name.take(2).uppercase().ifEmpty { "AR" },
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }

                            // Botón de cámara
                            Surface(
                                shape = CircleShape,
                                color = OrangeSecondary,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { /* TODO: Implementar cambio de foto */ }
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CameraAlt,
                                        contentDescription = "Cambiar foto",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Toca para cambiar foto",
                            fontSize = 13.sp,
                            color = TextSecondary
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Campo Nombre completo
                        Text(
                            text = "Nombre completo",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Tu nombre", fontSize = 14.sp) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Nombre",
                                    tint = TextLight
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TurquoisePrimary,
                                unfocusedBorderColor = TextLight.copy(alpha = 0.3f),
                                focusedLeadingIconColor = TurquoisePrimary
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Campo Correo electrónico
                        Text(
                            text = "Correo electrónico",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("tu@email.com", fontSize = 14.sp) },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = "Email",
                                    tint = TextLight
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TurquoisePrimary,
                                unfocusedBorderColor = TextLight.copy(alpha = 0.3f),
                                focusedLeadingIconColor = TurquoisePrimary
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Mensaje informativo
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = TurquoisePrimary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = "Info",
                                    tint = TurquoisePrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Tu información está segura y solo se usa para personalizar tu experiencia en TravelMarket.",
                                    fontSize = 12.sp,
                                    color = TextSecondary,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Guardar cambios
                Button(
                    onClick = {
                        keyboardController?.hide()

                        when {
                            name.isBlank() -> {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "El nombre no puede estar vacío",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            email.isBlank() -> {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "El email no puede estar vacío",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            else -> {
                                isLoading = true

                                val result = authRepository.updateUserProfile(name.trim(), email.trim())

                                when (result) {
                                    is com.tecsup.travelmarket.data.UpdateResult.Success -> {
                                        isLoading = false
                                        showSuccessSnackbar = true

                                        coroutineScope.launch {
                                            delay(500)
                                            navController.popBackStack()
                                        }
                                    }
                                    is com.tecsup.travelmarket.data.UpdateResult.Error -> {
                                        isLoading = false
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = result.message,
                                                duration = SnackbarDuration.Long
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TurquoisePrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !isLoading && name.isNotBlank() && email.isNotBlank()
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "Guardar cambios",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botón Cancelar
                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        fontSize = 15.sp,
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        // ✅ Snackbar flotante en la parte inferior
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }

    // Mostrar Snackbar de éxito
    LaunchedEffect(showSuccessSnackbar) {
        if (showSuccessSnackbar) {
            snackbarHostState.showSnackbar(
                message = "Perfil actualizado correctamente",
                duration = SnackbarDuration.Short
            )
            showSuccessSnackbar = false
        }
    }
}
