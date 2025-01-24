package com.example.cyberkafe.screen.home.testimonials

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class TestimonialsViewModel : ViewModel() {
    var testimonials by mutableStateOf<List<Testimonials>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun fetchTestimonials() {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("prod").document("testimonials")

        isLoading = true
        errorMessage = null

        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val testiMap = documentSnapshot.get("testimonials") as? Map<*, *>
                    testimonials = testiMap?.values?.mapNotNull { entry ->
                        val testimonialData = entry as? Map<*, *> ?: return@mapNotNull null
                        Testimonials(
                            name = testimonialData["name"] as? String ?: "",
                            review = testimonialData["review"] as? String ?: ""
                        )
                    } ?: emptyList()

                    Log.d("Firestore", "Fetched testimonials: $testimonials")
                } else {
                    errorMessage = "No testimonials found in the document."
                }
                isLoading = false
            }
            .addOnFailureListener { exception ->
                errorMessage = "Error fetching testimonials: ${exception.message}"
                isLoading = false
            }
    }
}

