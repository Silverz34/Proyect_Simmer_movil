package com.alixmontesinos.app_simmer.ui.screens.UserAuth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas
import com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen.RegisterViewModel
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import com.alixmontesinos.app_simmer.ui.theme.NunitoSansSemiBold
import com.alixmontesinos.app_simmer.ui.theme.YellowT



@Composable
fun Register (
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
){
    val state = viewModel.uiState

    Box(modifier = Modifier.fillMaxWidth().fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla ",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        FlechaRegreso(onBackClick = { navController.popBackStack() })


        Column(modifier = Modifier.fillMaxWidth().padding(top = 80.dp, bottom = 200.dp)) {
            Text(
                modifier = Modifier.padding(top = 45.dp, start = 50.dp),
                text = "Registrate",
                fontSize = 50.sp,
                fontFamily = ItimFont,
                color = Color.Black
            )



            Text(
                modifier = Modifier.padding(top = 35.dp, start = 50.dp),
                text = "Bienvenido, regístrate para conocer nuevas recetas",
                fontSize = 25.sp,
                fontFamily = NunitoSansSemiBold,
                color = Color.Black
            )

        }
        Card(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(600.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {

            Column(modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 30.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()))
            {
                // CAMPO USUARIO
                TextField(
                    modifier = Modifier.align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameChange(it) },
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
                                modifier = Modifier.size(30.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Nombre de Usuario",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }
                    }, colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    singleLine = true
                )

                if (state.usernameError != null) {
                    Text(text = state.usernameError!!, color = Color.Red, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // CAMPO EMAIL
                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),

                    shape = RoundedCornerShape(16.dp),

                    value = state.email,
                    onValueChange = { viewModel.onEmailChange(it) },

                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    placeholder = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = "E-mail",
                                tint = Color.Gray,
                                modifier = Modifier.size(30.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "E-mail",
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
                    ),
                    singleLine = true
                )

                if (state.emailError != null) {
                    Text(text = state.emailError!!, color = Color.Red, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Estado para la visibilidad de la contraseña
                var passwordVisible by remember { mutableStateOf(false) }
                // Estado para la visibilidad de la confirmación de contraseña
                var confirmPasswordVisible by remember { mutableStateOf(false) }

                // CAMPO PASSWORD
                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),

                    shape = RoundedCornerShape(16.dp),

                    value = state.password,
                    onValueChange = { viewModel.onPasswordChange(it) },

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
                                modifier = Modifier.size(30.dp)
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
                    ),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                             androidx.compose.material.icons.Icons.Filled.Visibility
                        else androidx.compose.material.icons.Icons.Filled.VisibilityOff

                        androidx.compose.material3.IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                        }
                    }
                )
                
                if (state.passwordError != null) {
                    Text(text = state.passwordError!!, color = Color.Red, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))


                // CAMPO CONFIRM PASSWORD
                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),

                    shape = RoundedCornerShape(16.dp),

                    value = state.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },

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
                                contentDescription = "Confirmar Contraseña",
                                tint = Color.Gray,
                                modifier = Modifier.size(30.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Confirmar Contraseña",
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
                    ),
                    singleLine = true,
                    visualTransformation = if (confirmPasswordVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible)
                             androidx.compose.material.icons.Icons.Filled.Visibility
                        else androidx.compose.material.icons.Icons.Filled.VisibilityOff

                        androidx.compose.material3.IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(imageVector = image, contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña")
                        }
                    }
                )

                if (state.confirmPasswordError != null) {
                    Text(text = state.confirmPasswordError!!, color = Color.Red, fontSize = 14.sp)
                }
                
                if (state.registerError != null) {
                    Text(text = state.registerError!!, color = Color.Red, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.tryRegister() },
                    enabled = !state.isLoading,
                    modifier = Modifier.width(230.dp).height(60.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),

                    ) {
                    if (state.isLoading) {
                        androidx.compose.material3.CircularProgressIndicator(color = YellowT)
                    } else {
                        Text(
                            text = "Registrarse",
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            color = YellowT
                        )
                    }
                }
                
                androidx.compose.runtime.LaunchedEffect(state.isSuccess) {
                    if (state.isSuccess) {
                        navController.navigate(OtrasRutas.Login.route) {
                            popUpTo(OtrasRutas.Register.route) { inclusive = true }
                        }
                        viewModel.onRegisterSuccessConsumed()
                    }
                }
            }

        }
    }

}