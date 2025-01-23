
package com.example.cyberkafe.screen.services

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ServicesViewModel : ViewModel() {
    var servicesList by mutableStateOf<List<Service>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set
    fun fetchServices() {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("ourServices").document("9rOlBOKOl7KKNN5lSO3M")

        isLoading = true
        errorMessage = null

        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val servicesMap = documentSnapshot.get("ourServices") as? Map<*, *>
                    val services = servicesMap?.values?.mapNotNull { serviceEntry ->
                        val serviceData = serviceEntry as? Map<*, *> ?: return@mapNotNull null
                        Service(
                            id = serviceData["id"] as? String ?: "",
                            header = serviceData["header"] as? String ?: "",
                            processingTime = serviceData["processingTime"] as? String ?: "",
                            requiredDocuments = (serviceData["requiredDocuments"] as? List<*>)?.mapNotNull { it as? String } ?: emptyList()
                        )
                    } ?: emptyList()

                    servicesList = services
                    Log.d("Firestore", "Fetched services: $services")
                } else {
                    errorMessage = "No services found in the document."
                    Log.d("Firestore", "No services found in the document.")
                }
                isLoading = false
            }
            .addOnFailureListener { exception ->
                errorMessage = "Error fetching data: ${exception.message}"
                isLoading = false
                Log.e("Firestore", "Error fetching data: ${exception.message}")
            }
        }
}

