package com.travelmarket.app.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val favoritePlaces: List<String> = emptyList(),
    val favoriteEvents: List<String> = emptyList(),
    val preferences: UserPreferences = UserPreferences()
)

data class UserPreferences(
    val language: String = "es",
    val notifications: Boolean = true,
    val darkMode: Boolean = false,
    val preferredCategories: List<String> = emptyList()
)
