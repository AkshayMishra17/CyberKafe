package com.example.cyberkafe.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cyberkafe.chat.view.ChatScreen
import com.example.cyberkafe.screen.ScreenThird
import com.example.cyberkafe.screen.home.HomeScreen
import com.example.cyberkafe.screen.services.ScreenSecond

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Home : Screen("home")

    object Chat: Screen("chat")
}

@Composable
fun CyberKafeNav() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
            .background(Color.Transparent),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.Transparent)
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("screen_1") {
                ScreenSecond()
            }
            composable("screen_2") {
                ScreenThird()
            }
            composable(Screen.Chat.route){
                ChatScreen(navController)
            }
        }
    }
}


