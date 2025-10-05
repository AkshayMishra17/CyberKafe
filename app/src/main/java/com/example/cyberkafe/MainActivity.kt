package com.example.cyberkafe

import com.example.cyberkafe.nav.AppNavHost
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.cyberkafe.screen.splash.SplashScreen
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
                var showSplashScreen by remember {   mutableStateOf(true)}
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000)
                    showSplashScreen = false
                }
                if (showSplashScreen) {
                    SplashScreen{
                        showSplashScreen = false
                    }
                } else {
                    AppNavHost(navController = navController, auth = firebaseAuth)
                }
            }
        }
    }
}
