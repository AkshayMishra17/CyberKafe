package com.example.cyberkafe.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthManager(onAuthenticated: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val isUserAuthenticated = remember { mutableStateOf(false) }
    val isCheckingAuthState = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            isUserAuthenticated.value = user != null
            isCheckingAuthState.value = false
        }

        auth.addAuthStateListener(authStateListener)
    }

    if (isCheckingAuthState.value) {
        LoadingScreen()
    } else if (isUserAuthenticated.value) {
        LaunchedEffect(Unit) {
            onAuthenticated()
        }
    } else {
        AuthScreen()
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator(color = Color(0xFF4682B4))
    }
}
