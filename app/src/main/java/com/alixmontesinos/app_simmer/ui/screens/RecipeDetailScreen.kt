package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import androidx.navigation.NavController
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.model.Recipe
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.components.FotoPerfilUniversal

@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: Int) {
    // NOTE: Replace this with a real ViewModel call
    val recipe = Recipe(
        id = 1,
        title = "Pancakes de Arándanos",
        author = "Alix Montesinos",
        imageRes = R.drawable.category_breakfast,
        time = "30 min",
        difficulty = "Fácil",
        calories = "250 kcal",
        description = "Unos deliciosos pancakes esponjosos perfectos para el desayuno.",
        ingredients = listOf("2 tazas de harina", "2 huevos", "1 taza de leche", "Arándanos frescos"),
        steps = listOf("Mezcla los ingredientes secos.", "Bate los huevos y la leche.", "Combina todo y cocina.")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Background Image (Header)
        Image(
            painter = painterResource(id = recipe.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter)
        )

        // 2. Overlay Buttons (Back and Bookmark)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlechaRegreso(onBackClick = { navController.popBackStack() })

            IconButton(
                onClick = { /* Bookmark logic */ },
                modifier = Modifier.background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_bookmark),
                    contentDescription = "Bookmark",
                    tint = Color.Black
                )
            }
        }

        // 3. Main Content (Scrollable)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 270.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = recipe.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                FotoPerfilUniversal(size=40.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "por ${recipe.author}", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                InfoChip(icon = R.drawable.candadito, label = recipe.time)
                InfoChip(icon = R.drawable.candadito, label = recipe.difficulty)
                InfoChip(icon = R.drawable.candadito, label = recipe.calories)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Ingredientes", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            recipe.ingredients.forEach { ingredient ->
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .padding(top = 6.dp)
                            .background(Color(0xFF4CAF50), shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = ingredient, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Preparación", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recipe.description, lineHeight = 24.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun InfoChip(icon: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color(0xFF4CAF50))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}
