package com.example.cyberkafe.screen.services

data class Service(
    val id: String,
    val header: String,
    val processingTime: String,
    val requiredDocuments: List<String>
)
