package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

val YellowHeader = Color(0xFFFFC93A)
val BackgroundColor = Color(0xFFFDFCF7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    Scaffold(
        topBar = { TopBarSection() },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            CategoriesSection()
            // Se le da un peso para que ocupe el espacio restante
            PopularRecipesSection(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(YellowHeader)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.user_avatar),
                    contentDescription = "Avatar de usuario",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Juan Pérez", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            }
            IconButton(
                onClick = { /* Acción para notificaciones */ },
                modifier = Modifier.background(Color.White, CircleShape)
            ) {
                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        var searchQuery by remember { mutableStateOf("") }
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
                onClick = { /* Acción de filtro */ },
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .size(56.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "Filtro")
            }
        }
    }
}

@Composable
fun CategoriesSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Categorías", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = ">", fontSize = 20.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            val categories = listOf(
                "Cena" to R.drawable.category_dinner,
                "Comida" to R.drawable.category_lunch,
                "Desayuno" to R.drawable.category_breakfast,
                "Postres" to R.drawable.category_dessert,
                "Snack" to R.drawable.category_snack
            )
            items(categories) { (name, imageRes) ->
                CategoryCard(name = name, imageRes = imageRes)
            }
        }
    }
}

@Composable
fun CategoryCard(name: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)))
        Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun PopularRecipesSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "Recetas populares", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            // Se elimina la altura fija para que la cuadrícula llene el espacio disponible
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            val recipes = listOf(
                "Huevito con arroz", "Pollo a la brasa", "Lomo Saltado", "Causa Limeña",
                "Aji de Gallina", "Ceviche"
            )
            items(recipes) { recipeName ->
                RecipeCard(name = recipeName)
            }
        }
    }
}

@Composable
fun RecipeCard(name: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = "Una pequeña receta...", color = Color.Gray, fontSize = 12.sp)
    }
}
