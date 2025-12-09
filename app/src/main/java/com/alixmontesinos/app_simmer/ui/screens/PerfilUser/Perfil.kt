package com.alixmontesinos.app_simmer.ui.screens.PerfilUser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alixmontesinos.app_simmer.model.perfilUser
import com.alixmontesinos.app_simmer.ui.components.FotoPerfilUniversal
import com.alixmontesinos.app_simmer.ui.components.RecipeCard
import com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen.ProfileViewModel


//de mientras
data class RecetaUiModel(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val rating: Double,
    val time: String,
    val difficulty: String,
    val author: String
)

val listaRecetasPrueba = listOf(
    RecetaUiModel(1, com.alixmontesinos.app_simmer.R.drawable.ic_launcher_background, "Tacos al Pastor", 4.8, "45 min", "Medio", "Alix M"),
    RecetaUiModel(2, com.alixmontesinos.app_simmer.R.drawable.ic_launcher_background, "Pozole Rojo", 5.0, "90 min", "Difícil", "Alix M"),
    RecetaUiModel(3, com.alixmontesinos.app_simmer.R.drawable.ic_launcher_background, "Guacamole", 4.5, "10 min", "Fácil", "Alix M")
)


@Composable
fun Perfil(uiState: perfilUser,
           onEditClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {

                ProfileTopBar()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 110.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileHeader(
                        name = uiState.username,
                        photoUrl = uiState.photoUrl
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileStats()
                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileDescription(uiState.description)
                    Spacer(modifier = Modifier.height(24.dp))
                    EditProfileButton(onEditClick = onEditClick)
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
        items(listaRecetasPrueba) { receta ->
            RecipeCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                imageRes = receta.imageRes,
                title = receta.title,
                rating = receta.rating,
                time = receta.time,
                difficulty = receta.difficulty,
                author = receta.author
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }


    }
}

@Composable
fun ProfileTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 20.dp)
            .padding(top = 20.dp)
            .height(150.dp)
            .background(Color(0xFFFFCA28),
             shape = RoundedCornerShape(20.dp))
    ) {}
}

@Composable
fun ProfileHeader(
    name : String,
    photoUrl : String

) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FotoPerfilUniversal(
            size = 100.dp,
            hasBorder = true,
            showCameraIcon = false
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(number = "8", label = "Recetas")
        VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp))
        StatItem(number = "45", label = "Guardados")
        VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp))
        StatItem(number = "4.9 k", label = "Me gusta")
    }
}

@Composable
fun StatItem(number: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Text(text = label,
            fontSize = 12.sp,
            color = Color.Gray)
    }
}

@Composable
fun ProfileDescription(description: String) {
    Text(
        text = description,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        color = Color.Gray,
        lineHeight = 20.sp
    )
}


@Composable
fun EditProfileButton(onEditClick: () -> Unit) {
    Button(
        onClick = onEditClick,
        modifier = Modifier
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFC107)
        ),
        shape = RoundedCornerShape(45.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Editar perfil",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray
) {
    Box(
        modifier = modifier.background(color)
    )
}

