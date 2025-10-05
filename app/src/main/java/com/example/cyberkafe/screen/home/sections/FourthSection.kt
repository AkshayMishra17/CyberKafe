package com.example.cyberkafe.screen.home.sections

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSection() {
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
            val context = LocalContext.current

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                MenuItem("About Us") {
                    openUrl(context, "https://cyberkafe.in/aboutus")
                }
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Contact") {
                    openUrl(context, "https://cyberkafe.in/contactus")
                }
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Privacy Policy") {
                    openUrl(context, "https://cyberkafe.in/privacypolicy")
                }
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem("Terms & Conditions") {
                    openUrl(context, "https://cyberkafe.in/termsandconditions")
                }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Crafted with ❤\uFE0F in Delhi")
        }
    }
}

@Composable
fun MenuItem(menuText: String, onClick: () -> Unit) {
    Text(
        text = menuText,
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal
        ),
        modifier = Modifier.clickable(onClick = onClick)
    )
}

fun openUrl(context: android.content.Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
