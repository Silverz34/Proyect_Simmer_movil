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
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import com.alixmontesinos.app_simmer.ui.theme.NunitoSans
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.CircularProgressIndicator

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

    val context = LocalContext.current

    LaunchedEffect(uiState.mensajeExito, uiState.mensajeError) {
        uiState.mensajeExito?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            onBackClick() // Regresar al home al guardar exitosamente
        }
        uiState.mensajeError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        
        if (uiState.isLoading) {
             Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(enabled = false) {}, // Bloquear clicks
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFFFC107))
            }
        }

        // Imagen de fondo / selección
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clickable { launcher.launch("image/*") }
        ) {
            AsyncImage(
                model = uiState.imageUri ?: R.drawable.cargarimagen,
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
                .height(670.dp),
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
                        fontWeight = FontWeight.Bold,
                        fontFamily = ItimFont,
                        color = Color.Black
                    )
                }

                // Titulo
                item {
                    OutlinedTextField(
                        value = uiState.titulo,
                        onValueChange = { viewModel.onTituloChange(it) },
                        label = { Text(text = "Nombre de la receta", fontFamily = NunitoSans, fontSize = 20.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontFamily = NunitoSans, color = Color.Black, fontSize = 20.sp)
                    )
                }

                // Descripcion
                item {
                    OutlinedTextField(
                        value = uiState.descripcion,
                        onValueChange = { viewModel.onDescripcionChange(it) },
                            label = { Text("Descripción", fontFamily = NunitoSans )},
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        maxLines = 5,
                        shape = RoundedCornerShape(16.dp),
                        textStyle = androidx.compose.ui.text.TextStyle(fontFamily = NunitoSans, color = Color.Black, fontSize = 20.sp)
                    )
                }

                // Seccion Ingredientes
                item {
                    Text(
                        text = "Ingredientes",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = ItimFont,
                        color = Color.Black,
                        fontSize = 20.sp,
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
                            placeholder = { Text("Ingrediente ${index + 1}", fontFamily = NunitoSans, fontSize = 20.sp) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            textStyle = androidx.compose.ui.text.TextStyle(fontFamily = NunitoSans, color = Color.Black, fontSize = 20.sp)
                        )
                        IconButton(onClick = { viewModel.eliminarIngrediente(index) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar ingrediente", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.agregarIngrediente() }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.agregarpaso),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Agregar Ingrediente",
                            fontFamily = ItimFont,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                // Seccion Pasos
                item {
                    Text(
                        text = "Pasos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = ItimFont,
                        color = Color.Black,
                        fontSize = 20.sp,
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
                            placeholder = { Text("Paso ${index + 1}", fontFamily = NunitoSans, fontSize = 20.sp) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            minLines = 2,
                            shape = RoundedCornerShape(16.dp),
                            textStyle = androidx.compose.ui.text.TextStyle(fontFamily = NunitoSans, color = Color.Black, fontSize = 20.sp)
                        )
                        IconButton(onClick = { viewModel.eliminarPaso(index) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar paso", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }

                item {
                     Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.agregarPaso() }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                         Icon(
                             painter = painterResource(id = R.drawable.agregarpaso),
                             contentDescription = null,
                             tint = Color.Unspecified,
                             modifier = Modifier.size(24.dp)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                         Text(
                             text = "Agregar Paso",
                             fontFamily = ItimFont,
                             color = Color.Black,
                             fontWeight = FontWeight.Bold,
                             fontSize = 18.sp
                         )
                    }
                }

                item {
                // Seccion Etiquetas
                    Text(
                        text = "Etiquetas :",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = ItimFont,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Tipo de comida:",
                        fontFamily = NunitoSans,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val etiquetasDisponibles = listOf("Snack", "Comida", "Desayuno", "Cena", "Almuerzo", "Postre")
                        etiquetasDisponibles.forEach { etiqueta ->
                            FilterChip(
                                selected = uiState.etiquetas.contains(etiqueta),
                                onClick = { viewModel.toggleEtiqueta(etiqueta) },
                                label = { Text(etiqueta, fontFamily = NunitoSans) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFFFC107), // Yellow-ish
                                    selectedLabelColor = Color.Black
                                ),
                                shape = RoundedCornerShape(50)
                            )
                        }
                    }
                }

                // Seccion Tiempo
                item {
                    Text(
                        text = "Tiempo de preparación aproximada:",
                        fontFamily = NunitoSans,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                    )
                    @OptIn(ExperimentalLayoutApi::class)
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val tiemposDisponibles = listOf("15 - 30 min", "15 min", "2 hr", "35 - 55 min", "60 - 75 min")
                        tiemposDisponibles.forEach { tiempo ->
                            FilterChip(
                                selected = uiState.tiempoPreparacion == tiempo,
                                onClick = { viewModel.setTiempoPreparacion(tiempo) },
                                label = { Text(tiempo, fontFamily = NunitoSans) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFFFC107),
                                    selectedLabelColor = Color.Black
                                ),
                                shape = RoundedCornerShape(50)
                            )
                        }
                    }
                }

                // Seccion Imagenes Extra
                item {
                    Text(
                        text = "Imágenes extra :",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = ItimFont,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    
                    val extraImagesLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent()
                    ) { uri: Uri? ->
                        viewModel.onExtraImageSelected(uri)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Mostrar imagenes seleccionadas + slot vacio si hay espacio (< 3)
                        val imagenesAMostrar = uiState.imagenesExtra.take(3)
                        imagenesAMostrar.forEachIndexed { index, uri ->
                             Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.LightGray, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = uri,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                // Opcion para borrar (overlay simple)
                                IconButton(
                                    onClick = { viewModel.removeExtraImage(index) },
                                    modifier = Modifier.align(Alignment.TopEnd)
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = Color.Red, modifier = Modifier.size(20.dp))
                                }
                            }
                        }

                        if (uiState.imagenesExtra.size < 3) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                                    .clickable { extraImagesLauncher.launch("image/*") },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_add), // Usando icon_add para el + simple
                                    contentDescription = "Agregar extra",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(23.dp)
                                )
                            }
                        }
                    }
                }

                // Botones Finales
                item {
                    var showCancelDialog by remember { mutableStateOf(false) }

                    if (showCancelDialog) {
                        AlertDialog(
                            onDismissRequest = { showCancelDialog = false },
                            title = { Text("¿Cancelar receta?", fontFamily = ItimFont) },
                            text = { Text("Si cancelas, perderás todos los datos ingresados.", fontFamily = NunitoSans) },
                            confirmButton = {
                                TextButton(onClick = {
                                    showCancelDialog = false
                                    onBackClick() // Navegar atrás (Home)
                                }) {
                                    Text("Sí, cancelar", color = Color.Red)
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showCancelDialog = false }) {
                                    Text("Volver")
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { showCancelDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(end = 8.dp)
                        ) {
                            Text("Cancelar", color = Color.White, fontFamily = ItimFont, fontSize = 18.sp)
                        }

                        Button(
                            onClick = { 
                                viewModel.guardarReceta() 
                                // Opcional: Navegar o mostrar éxito 
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Yellow
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(start = 8.dp)
                        ) {
                            Text("Crear Receta", color = Color.Black, fontFamily = ItimFont, fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}