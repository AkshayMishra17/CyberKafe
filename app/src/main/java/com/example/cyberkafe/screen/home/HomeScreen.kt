package com.example.cyberkafe.screen.home


import TopSection
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cyberkafe.screen.home.sections.BottomSection
import com.example.cyberkafe.screen.home.sections.ServicesSection
import com.example.cyberkafe.screen.home.sections.SubscriberSection
import com.example.cyberkafe.screen.home.sections.testimonials.TestimonialsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
//            TopBar(navController)
        },
       modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TopSection()
            }
            item{
                TestimonialsScreen()
            }
            item {
                ServicesSection(navController = navController)
            }
            item {
                SubscriberSection()
            }
            item {
                BottomSection()
            }
        }
    }
}



@Composable
fun ChatButton(navController: NavController){
    IconButton(onClick = {
        navController.navigate("chat")
    }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Chat"
        )
    }
}

@Composable
fun TopBar(navController: NavController){
    Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(
            text = "CyberKafe",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        ChatButton(navController)
    }
}