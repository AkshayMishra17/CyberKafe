package com.example.cyberkafe.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cyberkafe.screen.home.HomeScreen
import com.example.cyberkafe.screen.services.ScreenSecond
import com.example.cyberkafe.screen.ScreenThird
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.cyberkafe.chat.view.ChatScreen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val gradientAnim = remember { Animatable(0f) }

    LaunchedEffect(gradientAnim) {
        gradientAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 5000, delayMillis = 0)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color.Transparent),
            elevation = 8.dp
        ) {
            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate("home") },
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                alwaysShowLabel = true,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate("screen_1") },
                icon = { Icon(imageVector = Icons.Rounded.Info, contentDescription = "Services") },
                label = { Text("Services") },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate("screen_2") },
                icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Screen 2") },
                label = { Text("Screen 3") },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
        }
    }
}


