package com.alixmontesinos.app_simmer.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import com.alixmontesinos.app_simmer.ui.theme.MontserratsemiBoldFond
import com.alixmontesinos.app_simmer.ui.theme.NunitoSansSemiBold
import com.alixmontesinos.app_simmer.ui.theme.YellowT



@Composable
fun Register (navController: NavController){

    Box(modifier = Modifier.fillMaxWidth().fillMaxWidth()) {
        Image(
            painter = painterResource(id = com.alixmontesinos.app_simmer.R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla ",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        FlechaRegreso(navController)


        Column(modifier = Modifier.fillMaxWidth().padding(top = 100.dp, bottom = 200.dp)) {
            Text(
                modifier = Modifier.padding(top = 50.dp, start = 50.dp),
                text = "Registrate",
                fontSize = 50.sp,
                fontFamily = ItimFont,
                color = Color.Black
            )



            Text(
                modifier = Modifier.padding(top = 10.dp, start = 50.dp),
                text = "Bienvenido, regístrate para conocer nuevas recetas",
                fontSize = 25.sp,
                fontFamily = NunitoSansSemiBold,
                color = Color.Black
            )

        }
        Card(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(650.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {

            Column(modifier = Modifier.padding(50.dp).fillMaxWidth())
            {
                var Username by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.align(CenterHorizontally)
                        .fillMaxWidth().height(100.dp)
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    value = Username,
                    onValueChange = { Username = it },
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
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                var Email by remember { mutableStateOf("") }
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

                    value = Email,
                    onValueChange = { Email = it },

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
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                var Contraseña by remember { mutableStateOf("") }
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

                    value = Contraseña,
                    onValueChange = { Contraseña = it },

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
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))



                var ConfirmarContrsañea by remember { mutableStateOf("") }
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

                    value = ConfirmarContrsañea,
                    onValueChange = { ConfirmarContrsañea = it },

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
                    )
                )


                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {navController.navigate(route= OtrasRutas.Login.route)},
                    modifier = Modifier.width(230.dp).height(60.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),

                    ) {
                    Text(
                        text = "Registrarse",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = YellowT
                    )
                }
            }

        }
    }

}