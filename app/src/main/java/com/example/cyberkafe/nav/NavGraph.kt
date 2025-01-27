package com.example.cyberkafe.nav


sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Home : Screen("home")
}

