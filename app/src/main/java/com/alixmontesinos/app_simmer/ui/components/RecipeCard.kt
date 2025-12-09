package com.alixmontesinos.app_simmer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    imageRes: Int? = null,
    imageUrl: String = "",
    title: String,
    rating: Double = 0.0,
    time: String,
    difficulty: String,
    author: String
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()) {
                coil.compose.AsyncImage(
                    model = if (imageUrl.isNotEmpty()) imageUrl else (imageRes ?: R.drawable.cargarimagen),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Rating
                if (rating > 0) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = "Rating", tint = Color.Red)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = rating.toString(), fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }

                // Bookmark
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(Color.White, CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(Icons.Default.Bookmark, contentDescription = "Bookmark", tint = Color(0xFFFFC107))
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = "Time", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$time • $difficulty • Por $author", color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}