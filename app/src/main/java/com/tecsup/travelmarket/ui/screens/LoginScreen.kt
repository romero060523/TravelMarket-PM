package com.tecsup.travelmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.travelmarket.R
import com.tecsup.travelmarket.data.AuthRepositoryProvider
import com.tecsup.travelmarket.data.LoginResult
import com.tecsup.travelmarket.model.UserCredentials
import com.tecsup.travelmarket.navigation.Screen
import com.tecsup.travelmarket.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val authRepository = remember { AuthRepositoryProvider.authRepository }
    val keyboardController = LocalSoftwareKeyboardController.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo/Icono
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "Logo",
            tint = TurquoisePrimary,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "TravelMarket",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = TurquoisePrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Descubre los mejores lugares de Lima",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Campo de Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            placeholder = { Text("correo@ejemplo.com") },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TurquoisePrimary,
                focusedLabelColor = TurquoisePrimary
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            placeholder = { Text("••••••••") },
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Contraseña"
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // Intentar login
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TurquoisePrimary,
                focusedLabelColor = TurquoisePrimary
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensaje de error
        if (errorMessage != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Error,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Olvidé mi contraseña (opcional)
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = TurquoisePrimary,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* TODO: Implementar recuperación */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Iniciar Sesión
        Button(
            onClick = {
                keyboardController?.hide()

                // Validaciones básicas
                when {
                    email.isBlank() -> {
                        errorMessage = "Ingresa tu email"
                    }
                    password.isBlank() -> {
                        errorMessage = "Ingresa tu contraseña"
                    }
                    else -> {
                        isLoading = true
                        errorMessage = null

                        // Intentar login
                        val credentials = UserCredentials(email, password)
                        val result = authRepository.login(credentials)

                        when (result) {
                            is LoginResult.Success -> {
                                // Login exitoso - navegar al home
                                navController.navigate(Screen.Home.route) {
                                    // Limpiar el back stack para que no pueda volver al login
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            }
                            is LoginResult.Error -> {
                                errorMessage = result.message
                                isLoading = false
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TurquoisePrimary
            ),
            shape = RoundedCornerShape(12.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text(
                    text = "Iniciar Sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Registro
        OutlinedButton(
            onClick = {
                navController.navigate(Screen.Register.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TurquoisePrimary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Crear cuenta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Credenciales de prueba
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = BackgroundGray
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Usuario de prueba:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Email: romero@gmail.com",
                    fontSize = 11.sp,
                    color = TextSecondary
                )
                Text(
                    text = "Contraseña: romero23",
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }
        }
    }
}