package com.example.cyberkafe.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FourthSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C))
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = "Cyber Kafe",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(12.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MenuItem("About Us")
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Contact")
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Privacy Policy")
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Terms of Service")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "© 2025 Cyber Kafe. All rights reserved.",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Box(
        modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.White)
            .height(50.dp),
            contentAlignment = Alignment.Center
        ){
            Text("Crafted with ❤\uFE0F in Delhi")
        }
    }
}

@Composable
fun MenuItem(menuText: String) {
    Text(
        text = menuText,
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal
        ),
        modifier = Modifier.clickable(onClick = { /* Handle click */ })
    )
}
