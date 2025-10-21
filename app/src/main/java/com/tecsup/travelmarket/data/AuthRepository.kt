package com.tecsup.travelmarket.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tecsup.travelmarket.model.User
import com.tecsup.travelmarket.model.UserCredentials
import com.tecsup.travelmarket.model.RegisterData
import kotlin.collections.containsKey
import kotlin.collections.remove
import kotlin.text.get
import kotlin.text.set

/**
 * Repository para manejo de autenticación
 * Nota: Esta es una implementación simple con datos en memoria
 * En producción deberías usar una base de datos o API
 */
class AuthRepository {

    // Lista de usuarios registrados (simulado)
    private val registeredUsers = mutableListOf(
        User(
            id = 1,
            name = "Andy Romero Poma",
            email = "romero@gmail.com",
            phone = "+51 901 935 409"
        )
    )

    // Mapa de credenciales (email -> password)
    private val userCredentials = mutableMapOf(
        "romero@gmail.com" to "romero23"
    )

    // Usuario actualmente autenticado
    private var currentUser: User? = null

    /**
     * Iniciar sesión
     * @return User si las credenciales son correctas, null si no
     */
    fun login(credentials: UserCredentials): LoginResult {
        val storedPassword = userCredentials[credentials.email]

        return when {
            storedPassword == null -> {
                LoginResult.Error("Usuario no encontrado")
            }
            storedPassword != credentials.password -> {
                LoginResult.Error("Contraseña incorrecta")
            }
            else -> {
                val user = registeredUsers.find { it.email == credentials.email }
                if (user != null) {
                    currentUser = user
                    LoginResult.Success(user)
                } else {
                    LoginResult.Error("Error al cargar usuario")
                }
            }
        }
    }

    /**
     * Registrar nuevo usuario
     */
    fun register(data: RegisterData): RegisterResult {
        // Validaciones
        if (data.name.isBlank()) {
            return RegisterResult.Error("El nombre es obligatorio")
        }

        if (data.email.isBlank() || !isValidEmail(data.email)) {
            return RegisterResult.Error("Email inválido")
        }

        if (data.password.length < 6) {
            return RegisterResult.Error("La contraseña debe tener al menos 6 caracteres")
        }

        if (data.password != data.confirmPassword) {
            return RegisterResult.Error("Las contraseñas no coinciden")
        }

        // Verificar si el email ya existe
        if (userCredentials.containsKey(data.email)) {
            return RegisterResult.Error("Este email ya está registrado")
        }

        // Crear nuevo usuario
        val newUser = User(
            id = registeredUsers.size + 1,
            name = data.name,
            email = data.email,
            phone = data.phone
        )

        // Guardar usuario y credenciales
        registeredUsers.add(newUser)
        userCredentials[data.email] = data.password

        return RegisterResult.Success(newUser)
    }

    /**
     * Cerrar sesión
     */
    fun logout() {
        currentUser = null
    }

    /**
     * Obtener usuario actual
     */
    fun getCurrentUser(): User? = currentUser

    /**
     * Verificar si hay sesión activa
     */
    fun isLoggedIn(): Boolean = currentUser != null

    /**
     * Validar formato de email
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun updateUserProfile(newName: String, newEmail: String): UpdateResult {
        val currentUser = this.currentUser ?: return UpdateResult.Error("No hay sesión activa")

        // Validaciones
        if (newName.isBlank()) {
            return UpdateResult.Error("El nombre no puede estar vacío")
        }

        if (newEmail.isBlank() || !isValidEmail(newEmail)) {
            return UpdateResult.Error("Email inválido")
        }

        // Verificar si el nuevo email ya existe (si es diferente al actual)
        if (newEmail != currentUser.email && userCredentials.containsKey(newEmail)) {
            return UpdateResult.Error("Este email ya está en uso por otra cuenta")
        }

        // Actualizar el usuario en la lista
        val userIndex = registeredUsers.indexOfFirst { it.id == currentUser.id }
        if (userIndex == -1) {
            return UpdateResult.Error("Usuario no encontrado")
        }

        // Crear usuario actualizado
        val updatedUser = currentUser.copy(
            name = newName.trim(),
            email = newEmail.trim()
        )

        // Actualizar en la lista de usuarios registrados
        registeredUsers[userIndex] = updatedUser

        // Si el email cambió, actualizar las credenciales
        if (newEmail != currentUser.email) {
            val password = userCredentials[currentUser.email]
            if (password != null) {
                userCredentials.remove(currentUser.email)
                userCredentials[newEmail] = password
            }
        }

        // Actualizar el usuario actual
        this.currentUser = updatedUser

        return UpdateResult.Success(updatedUser)
    }
}

/**
 * Resultado de la actualización de perfil
 */
sealed class UpdateResult {
    data class Success(val user: User) : UpdateResult()
    data class Error(val message: String) : UpdateResult()
}


/**
 * Resultado del login
 */
sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Error(val message: String) : LoginResult()
}

/**
 * Resultado del registro
 */
sealed class RegisterResult {
    data class Success(val user: User) : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}

/**
 * Singleton para acceso global
 */
object AuthRepositoryProvider {
    val authRepository: AuthRepository by lazy {
        AuthRepository()
    }
}