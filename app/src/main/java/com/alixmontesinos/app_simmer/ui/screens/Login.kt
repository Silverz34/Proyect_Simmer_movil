package com.alixmontesinos.app_simmer.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController

import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas
import com.alixmontesinos.app_simmer.ui.theme.BlancoCard
import com.alixmontesinos.app_simmer.ui.theme.MontserratSemiregularFond
import com.alixmontesinos.app_simmer.ui.theme.MontserratsemiBoldFond
import com.alixmontesinos.app_simmer.ui.theme.YellowT
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alixmontesinos.app_simmer.ui.login.LoginViewModel



@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState

    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla ",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        FlechaRegreso(navController)

        Column(
            modifier = Modifier
                .padding(top = 120.dp, bottom = 200.dp)
                .fillMaxWidth()
        ) {

            Text(
                modifier = Modifier.padding(top = 40.dp, start = 60.dp),
                text = "Inicio de \nsesión",
                fontSize = 60.sp,
                lineHeight = 50.sp,
                fontFamily = ItimFont,
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(top = 40.dp, start = 60.dp))

            Text(
                modifier = Modifier.padding(horizontal = 60.dp),
                text = "Bienvenido, ingresa para ver las recetas del dia",
                fontSize = 33.sp,
                lineHeight = 30.sp,
                fontFamily = ItimFont,
                color = Color.Black
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(480.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(containerColor = BlancoCard)
        ) {
            Column(
                modifier = Modifier
                    .padding(50.dp)
                    .fillMaxWidth()
            ) {

                // ───────────── CAMPO USUARIO ─────────────
                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    value = state.username,                           // 👈 lee del ViewModel
                    onValueChange = { viewModel.onUsernameChange(it) }, // 👈 avisa al ViewModel
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_user),
                                contentDescription = "Nombre de Usuario",
                                tint = Color.Gray,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Nombre de Usuario",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                )

                // Mensaje de error de usuario (si aplica)
                if (state.usernameError != null) {
                    Text(
                        text = state.usernameError!!,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // ───────────── CAMPO CONTRASEÑA ─────────────
                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    value = state.password,                             // 👈 del ViewModel
                    onValueChange = { viewModel.onPasswordChange(it) }, // 👈 al ViewModel
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.candadito),
                                contentDescription = "Contraseña",
                                tint = Color.Gray,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Contraseña",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    )
                )

                // Mensaje de error de contraseña (si aplica)
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError!!,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ───────────── BOTÓN INICIAR SESIÓN ─────────────
                Button(
                    onClick = {
                        val ok = viewModel.tryLogin()
                        if (ok) {
                            // TODO: cambia "home" por tu ruta real, por ejemplo:
                            // navController.navigate(OtrasRutas.Home.route)
                            navController.navigate("home")
                        }
                    },
                    enabled = viewModel.isFormValid(),  // 👈 solo si campos válidos
                    modifier = Modifier
                        .width(230.dp)
                        .height(60.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        disabledContainerColor = Color.Gray // opcional: botón gris si inválido
                    ),
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = YellowT
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(Modifier.align(CenterHorizontally)) {
                    Text(
                        text = "¿No tienes una cuenta?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Registrate aquí",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = YellowT,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(OtrasRutas.Register.route)
                        }
                    )
                }
            }
        }
    }
}
