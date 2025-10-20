package com.tecsup.travelmarket.data

/**
 * RepositoryProvider - Singleton para acceso global al Repository
 * Garantiza una única instancia del repositorio en toda la aplicación
 */
object RepositoryProvider {

    //Instancia única del Repository
    //Lazy initialization - se crea solo cuando se necesita
    val repository: Repository by lazy {
        Repository()
    }

}