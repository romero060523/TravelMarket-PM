package com.travelmarket.app.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Places : Screen("places")
    object Events : Screen("events")
    object Services : Screen("services")
    object Profile : Screen("profile")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: String) = "place_detail/$placeId"
    }
    object EventDetail : Screen("event_detail/{eventId}") {
        fun createRoute(eventId: String) = "event_detail/$eventId"
    }
    object ServiceDetail : Screen("service_detail/{serviceId}") {
        fun createRoute(serviceId: String) = "service_detail/$serviceId"
    }
    object Search : Screen("search")
}
