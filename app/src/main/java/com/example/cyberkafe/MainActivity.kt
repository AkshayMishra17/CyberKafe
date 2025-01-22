package com.example.cyberkafe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.example.cyberkafe.nav.CyberKafeNav
import com.example.cyberkafe.ui.theme.CyberKafeTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            CyberKafeTheme {
                 CyberKafeNav()

            }
        }
    }
}
val LightBlue = Color(0xFF81D4FA)  // Light Blue
val SkyBlue = Color(0xFF64B5F6)    // Sky Blue
val DodgerBlue = Color(0xFF2196F3) // Dodger Blue
val DeepBlue = Color(0xFF1565C0)   // Deep Blue
val NavyBlue = Color(0xFF0D47A1)   // Navy Blue
val SteelBlue = Color(0xFF4682B4)  // Steel Blue
val CyanBlue = Color(0xFF00BCD4)   // Cyan Blue
val AquaBlue = Color(0xFF00ACC1)   // Aqua Blue
val MidnightBlue = Color(0xFF003366)