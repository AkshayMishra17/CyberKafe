package com.example.cyberkafe.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cyberkafe.auth.AuthManager
import com.example.cyberkafe.chat.view.ChatScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavHost(navController: NavHostController, auth: FirebaseAuth) {
    val currentUser = auth.currentUser

    val startDestination:String = if(currentUser == null){
        Screen.Auth.route
    }else{
        Screen.Home.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Auth.route) {
            AuthManager(navController = navController, auth = auth)
        }
        composable(Screen.Home.route) {
              CyberKafeNav()
        }
    }
}
