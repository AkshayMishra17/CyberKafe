package com.example.cyberkafe.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthManager(navController: NavController, auth: FirebaseAuth) {
    val viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = AuthViewModelFactory(auth)
    )
    val isUserAuthenticated = viewModel.isUserAuthenticated.collectAsState(initial = false)
    val isCheckingAuthState = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser

            isCheckingAuthState.value = false

            if (user != null) {
                navController.navigate("home") {
                    popUpTo("auth") { inclusive = true }
                }
            }
        }
        auth.addAuthStateListener(authStateListener)
    }
    if (isCheckingAuthState.value) {
        LoadingScreen()
    }else {
        if (!isUserAuthenticated.value) {
            AuthScreen()
        }
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
