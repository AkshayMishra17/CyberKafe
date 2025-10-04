package com.example.cyberkafe.screen.services

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSecond() {
    val viewModel: ServicesViewModel = viewModel()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchServices()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Our Services",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f)
                            )
                            TextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                placeholder = {
                                    Text(
                                        "Search...",
                                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search Icon",
                                        tint = Color.Gray
                                    )
                                },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .padding(horizontal = 8.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xFFFFDDEE),
                                    cursorColor = Color.Black,
                                    focusedIndicatorColor = Color(0xFFFF88CC),
                                    unfocusedIndicatorColor = Color(0xFFCCCCCC)
                                )
                            )


                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFFDDEE)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                viewModel.errorMessage != null -> {
                    Text(
                        text = "Error: ${viewModel.errorMessage}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    val filteredServices = viewModel.servicesList.filter {
                        it.header.contains(searchQuery, ignoreCase = true)
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredServices) { service ->
                            ServiceCard(service = service)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceCard(service: Service) {
    var showDocuments by remember { mutableStateOf(false) }

    val lightPink = Color(0xFFFFDDEE)
    val darkTextColor = Color(0xFF333333)
    val lightTextColor = Color(0xFF666666)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFf3f4f6))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Service: ${service.header}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = darkTextColor
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Processing Time: ${service.processingTime}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = lightTextColor
                ),
                textAlign = TextAlign.Center
            )

            ElevatedButton(
                onClick = { showDocuments = !showDocuments },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = lightPink)
            ) {
                Text(
                    text = if (showDocuments) "Hide Documents" else "Show Required Documents",
                    color = darkTextColor
                )
            }

            if (showDocuments) {
                service.requiredDocuments.forEach { document ->
                    Text(
                        text = "- $document",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = darkTextColor
                        ),
                        modifier = Modifier.padding(start = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
