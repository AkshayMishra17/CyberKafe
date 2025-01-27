package com.example.cyberkafe.screen.home.sections

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdSection() {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val firestore = Firebase.firestore
    Box(
        modifier = Modifier
            .height(250.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFE6EB),
                        Color(0xFFFFFFFF)
                    ),
                    start = Offset(0f, 50f),
                    end = Offset(0f,100f)
                )
            )
            .padding(vertical = 24.dp, horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Subscribe to our newsletter",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = Color.Black
                )
            )

            Text(
                text = "Stay updated with the latest news and exclusive offers directly in your inbox",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it},
                    placeholder = {
                        Text(
                            text = "Enter your email",
                            style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    singleLine = true
                )

                ElevatedButton(
                    onClick = {
                        if(email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            val data = hashMapOf(
                                "email" to email,
                                "subscribedAt" to java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.getDefault())
                                    .apply { timeZone = java.util.TimeZone.getTimeZone("UTC") }
                                    .format(System.currentTimeMillis()),
                                "createdAt" to java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.getDefault())
                                    .apply { timeZone = java.util.TimeZone.getTimeZone("UTC") }
                                    .format(System.currentTimeMillis())
                            )
                            firestore.collection("prod/newsletters/items")
                                     .document(email)
                                     .set(data)
                                .addOnSuccessListener {
                                    Toast.makeText(context,"Subscribed Successfully",Toast.LENGTH_SHORT).show()
                                    email = ""
                                }
                                .addOnFailureListener{
                                    Toast.makeText(context,"Error:${it.message}",Toast.LENGTH_SHORT).show()
                                }
                        }else{
                            Toast.makeText(context,"Enter a valid email",Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.height(60.dp)
                ) {
                    Text("Subscribe", style = TextStyle(fontSize = 16.sp))
                }
            }
        }
    }
}

