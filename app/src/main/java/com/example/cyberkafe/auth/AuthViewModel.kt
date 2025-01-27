package com.example.cyberkafe.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel(private val auth:FirebaseAuth):ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading:StateFlow<Boolean>get() = _isLoading

    private val _message = MutableStateFlow<String?>(null)
    val message: MutableStateFlow<String?>
        get() = _message

    private val _isUserAuthenticated = MutableStateFlow(false)
    var isUserAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    fun signInUser(email: String, password: String, context: Context) {
    viewModelScope.launch {
        _isLoading.value = true
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            _message.value = "Welcome back!"
        } catch (e: Exception) {
            _message.value = when (e) {
                is FirebaseAuthInvalidUserException -> "No user found with this email."
                is FirebaseAuthInvalidCredentialsException -> "Invalid email or password."
                else -> "Login Failed: ${e.message}"
            }
        } finally {
            _isLoading.value = false
        }
    }
}
        fun signUpUser(email: String, password: String, context: Context) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    _message.value = "User account created successfully!"
                } catch (e: Exception) {
                    _message.value = when (e) {
                        is FirebaseAuthWeakPasswordException -> "Password is too weak. Please choose a stronger password."
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email format. Please enter a valid email."
                        is FirebaseAuthUserCollisionException -> "An account with this email already exists."
                        else -> "Sign Up Failed: ${e.message}"
                    }
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun clearMessage(){
            _message.value = null
        }
}
