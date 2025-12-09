package com.alixmontesinos.app_simmer.ui.screens.PerfilUser

import android.net.Uri
import android.window.BackEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.model.perfilUser
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.components.FotoPerfilUniversal


val YellowHeader = Color(0xFFFFCA28)
val BackgroundGray = Color(0xFFF5F5F5)
val TextGray = Color(0xFF8E8E93)
val TextBlack = Color(0xFF1C1C1E)
val LinkBlue = Color(0xFF2196F3)

@Composable
fun EditarPerfil(uiState: perfilUser,
                 onPhotoChange: (Uri) -> Unit,
                 onDescriptionClick: () -> Unit,
                 onBackClick: () -> Unit){
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onPhotoChange(it) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderSection(onBackClick = onBackClick)
        Spacer(modifier = Modifier.height(24.dp))
        ProfilePhoto(
            onChangeClick = { launcher.launch("image/*")}
        )
        Spacer(modifier = Modifier.height(32.dp))
        NamesCardSection(name = uiState.username)
        Spacer(modifier = Modifier.height(24.dp))
        BasicInfoSection(
            currentDescription = uiState.description,
            onDescriptionClick = onDescriptionClick
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun HeaderSection(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(135.dp)
            .background(
                color = YellowHeader,
                shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
            )
    ) {
        FlechaRegreso(onBackClick = onBackClick)

        Text(
            text = "Edición del perfil",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = TextBlack,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 24.dp)
        )
    }
}

@Composable
fun ProfilePhoto(onChangeClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onChangeClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            FotoPerfilUniversal(size = 120.dp, hasBorder = false, showCameraIcon = true)
        }

        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = onChangeClick) {
            Text(text = "Cambiar foto", color = LinkBlue, fontWeight = FontWeight.Medium)
        }
    }
}
@Composable
fun NamesCardSection(name : String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileInfoRow(label = "Nombre", value = name)
        }
    }
}

@Composable
fun BasicInfoSection(
    currentDescription: String,
    onDescriptionClick: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = "Información básica",
            fontWeight = FontWeight.Medium,
            color = TextBlack,
            modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
        )
        Card(
            onClick = onDescriptionClick,
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Descripción corta",
                        color = TextGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentDescription,
                        color = TextBlack,
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Ver más",
                    tint = TextGray,
                    modifier = Modifier.size(18.dp).padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = TextGray,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
        Text(
            text = value,
            color = TextBlack,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}