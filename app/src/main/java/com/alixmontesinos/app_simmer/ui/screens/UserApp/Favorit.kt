package com.alixmontesinos.app_simmer.ui.screens.UserApp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.components.RecipeCard
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas

// Dummy data class for favorites
data class FavoriteRecipe(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val rating: Double,
    val time: String,
    val difficulty: String,
    val author: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorit(navController: NavController) { // Add NavController
    val BackgroundColor = Color(0xFFFDFCF7)

    // Dummy list of favorite recipes
    val favoriteRecipes = remember {
        listOf(
            FavoriteRecipe(1, R.drawable.rigatoni_pasta, "Rigatoni Pasta", 4.9, "35 min", "Media", "Alix M"),
            FavoriteRecipe(2, R.drawable.category_breakfast, "Pancakes", 4.8, "20 min", "Fácil", "John D"),
            FavoriteRecipe(3, R.drawable.category_dinner, "Salmon", 4.9, "40 min", "Media", "Jane S")
        )
    }

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
            items(favoriteRecipes) { recipe -> // use items with the list
                RecipeCard(
                    imageRes = recipe.imageRes,
                    title = recipe.title,
                    rating = recipe.rating,
                    time = recipe.time,
                    difficulty = recipe.difficulty,
                    author = recipe.author,
                    modifier = Modifier.clickable { // Make the card clickable
                        navController.navigate(OtrasRutas.RecipeDetail.createRoute(recipe.id))
                    }
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
        // Top Row: Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            // FlechaRegreso removed
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
