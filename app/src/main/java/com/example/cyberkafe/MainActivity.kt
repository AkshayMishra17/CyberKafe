package com.example.cyberkafe

import com.example.cyberkafe.nav.AppNavHost
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.cyberkafe.ui.theme.CyberKafeTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val firebaseAuth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContent {
            CyberKafeTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController,auth = firebaseAuth)
            }
        }
    }
}
