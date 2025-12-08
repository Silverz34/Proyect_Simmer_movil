package com.alixmontesinos.app_simmer.ui.screens.UserApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
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
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.ViewModel.Category
import com.alixmontesinos.app_simmer.ui.ViewModel.HomeViewModel
import com.alixmontesinos.app_simmer.ui.ViewModel.Recipe
import kotlinx.coroutines.launch

val YellowHeader = Color(0xFFFFC93A)
val BackgroundColor = Color(0xFFFDFCF7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(homeViewModel: HomeViewModel = viewModel()) {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
         androidx.compose.material3.Text("Home Works - Debug Mode")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection(onFilterClick: () -> Unit) {
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
                onClick = onFilterClick,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = BackgroundColor
    ) {
        val timeOptions = listOf("30 min", "15 min", "1 h")
        val (selectedTime, onTimeSelected) = remember { mutableStateOf<String?>(null) }

        val difficultyOptions = listOf("Fácil", "Media", "Difícil")
        val (selectedDifficulty, onDifficultySelected) = remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter Icon", tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Filtros", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                }
            }
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Filter Groups
            FilterGroup(
                title = "Tiempo",
                options = timeOptions,
                selectedOption = selectedTime,
                onOptionSelected = onTimeSelected
            )
            Spacer(modifier = Modifier.height(16.dp))
            FilterGroup(
                title = "Dificultad",
                options = difficultyOptions,
                selectedOption = selectedDifficulty,
                onOptionSelected = onDifficultySelected
            )

            Spacer(modifier = Modifier.weight(1f))

            // Apply Button
            Button(
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = YellowHeader)
            ) {
                Text("Aplicar", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FilterGroup(
    title: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Column {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        options.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = YellowHeader,
                        unselectedColor = Color.Gray
                    )
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp), color = Color.Black)
            }
        }
    }
}


@Composable
fun CategoriesSection(categories: List<Category>) {
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
            items(categories) { category ->
                CategoryCard(name = category.name, imageRes = category.imageRes)
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
        coil.compose.AsyncImage(
            model = imageRes,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)))
        Text(text = name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun PopularRecipesSection(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "Recetas populares", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(recipes) { recipe ->
                PopularRecipeItem(recipe = recipe)
            }
        }
    }
}

@Composable
fun PopularRecipeItem(recipe: Recipe) {
    Column {
        coil.compose.AsyncImage(
            model = recipe.imageRes,
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = recipe.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = recipe.description, color = Color.Gray, fontSize = 12.sp)
    }
}
