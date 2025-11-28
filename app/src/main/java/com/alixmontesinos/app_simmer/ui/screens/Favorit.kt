package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorit() {
    val YellowHeader = Color(0xFFFFC93A)
    val BackgroundColor = Color(0xFFFDFCF7)

    Scaffold(
        topBar = { FavoriteTopBar() },
        containerColor = BackgroundColor
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(3) { // Dummy data for now
                RecipeCard(
                    imageRes = R.drawable.rigatoni_pasta, // Replace with your actual image
                    title = "Rigatoni Pasta",
                    rating = 4.9,
                    time = "35 min",
                    difficulty = "Media",
                    author = "Alix M"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar() {
    val YellowHeader = Color(0xFFFFC93A)
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(YellowHeader)
            .padding(16.dp)
    ) {
        // Top Row: Back Arrow and Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), // Add some vertical padding
            contentAlignment = Alignment.Center
        ) {
            FlechaRegreso(
                modifier = Modifier.align(Alignment.CenterStart),
                onBackClick = { /* Handle back navigation */ }
            )
            Text(
                text = "Favoritos",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Row: Search and Filter
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar receta", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { /* Handle filter click */ },
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filtro"
                )
            }
        }
    }
}