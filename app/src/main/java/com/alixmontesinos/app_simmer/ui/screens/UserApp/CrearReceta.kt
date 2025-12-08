package com.alixmontesinos.app_simmer.ui.screens.UserApp

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.theme.BlancoCard
import com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen.CrearRecetaViewModel

@Composable
fun CrearReceta(onBackClick: () -> Unit,
    viewModel: CrearRecetaViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onImageSelected(uri)
    }

    Box(modifier = Modifier.fillMaxWidth()) {

        // Imagen de fondo / selección
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clickable { launcher.launch("image/*") }
        ) {
            AsyncImage(
                model = uiState.imageUri ?: R.drawable.guerreroz, 
                contentDescription = "Imagen de la receta",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            if (uiState.imageUri == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Agregar foto",
                        color = Color.White
                    )
                }
            }
        }

        FlechaRegreso(
            onBackClick = onBackClick
        )

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.75f),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            ),
            colors = CardDefaults.cardColors(containerColor = BlancoCard)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Crear nueva receta",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Titulo
                item {
                    OutlinedTextField(
                        value = uiState.titulo,
                        onValueChange = { viewModel.onTituloChange(it) },
                        label = { Text("Nombre de la receta") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                // Descripcion
                item {
                    OutlinedTextField(
                        value = uiState.descripcion,
                        onValueChange = { viewModel.onDescripcionChange(it) },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        maxLines = 5
                    )
                }

                // Seccion Ingredientes
                item {
                    Text(
                        text = "Ingredientes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                itemsIndexed(uiState.ingredientes) { index, ingrediente ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = ingrediente,
                            onValueChange = { viewModel.onIngredienteChange(index, it) },
                            placeholder = { Text("Ingrediente ${index + 1}") },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )
                        IconButton(onClick = { viewModel.eliminarIngrediente(index) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar ingrediente", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }

                item {
                    Button(
                        onClick = { viewModel.agregarIngrediente() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Agregar Ingrediente")
                    }
                }

                // Seccion Pasos
                item {
                    Text(
                        text = "Pasos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                itemsIndexed(uiState.pasos) { index, paso ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = paso,
                            onValueChange = { viewModel.onPasoChange(index, it) },
                            placeholder = { Text("Paso ${index + 1}") },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            minLines = 2
                        )
                        IconButton(onClick = { viewModel.eliminarPaso(index) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar paso", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }

                item {
                    Button(
                        onClick = { viewModel.agregarPaso() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Agregar Paso")
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.guardarReceta() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Guardar Receta")
                    }
                    Spacer(modifier = Modifier.height(30.dp)) // Espacio final
                }
            }
        }
    }
}