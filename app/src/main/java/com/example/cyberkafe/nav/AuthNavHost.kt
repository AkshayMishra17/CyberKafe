package com.example.cyberkafe.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cyberkafe.auth.AuthManager
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavHost(navController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController = navController, startDestination = Screen.Auth.route) {
        composable(Screen.Auth.route) {
            AuthManager(navController = navController, auth = auth)
        }
        composable(Screen.Home.route) {
//            HomeScreen(navController = navController)
              CyberKafeNav()
        }
    }
}
