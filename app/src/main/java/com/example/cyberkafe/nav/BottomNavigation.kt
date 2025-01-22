package com.example.cyberkafe.nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cyberkafe.screen.home.HomeScreen
import com.example.cyberkafe.screen.services.ScreenSecond
import com.example.cyberkafe.screen.ScreenThird

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("screen_1") },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Screen 1") },
            label = { Text("Screen 1") }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("screen_2") },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Screen 2") },
            label = { Text("Screen 2") }
        )
    }
}
@Composable
fun CyberKafeNav() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("screen_1") {
                ScreenSecond()
            }
            composable("screen_2") {
                ScreenThird()
            }
        }
    }
}
