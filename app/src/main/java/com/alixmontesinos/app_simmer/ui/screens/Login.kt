package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally

import com.alixmontesinos.app_simmer.ui.components.FlechaRegreso




@Preview
@Composable
fun Login() {

    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla ",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

    FlechaRegreso()



        Column (
                modifier = Modifier
                    .padding(top = 120.dp, bottom = 200.dp)
                    .fillMaxWidth()
                    ){

            Text(modifier = Modifier.padding(top = 40.dp, start = 60.dp),
                text = "Inicio de \nsesión",
                fontSize = 50.sp,
                lineHeight = 50.sp,
                fontFamily = ItimFont
            )

            Spacer(modifier = Modifier.padding(top = 40.dp, start = 60.dp))

            Text(
                modifier = Modifier.padding(horizontal = 60.dp),
                text = "Bienvenido, ingresa para ver las recetas del dia",
                fontSize = 25.sp,
                lineHeight = 30.sp,
                fontFamily = ItimFont,
            )
        }

        Card (modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(450.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {
            Column (modifier = Modifier.padding(40.dp).fillMaxWidth())
            {
                var Username by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.align(CenterHorizontally).fillMaxWidth().height(67.dp),
                    shape = RoundedCornerShape(16.dp),
                    value = Username,
                    onValueChange = { Username = it },
                    label = { Text("Nombre de Usuario") }
                )

                    Spacer(modifier = Modifier.height(40.dp))

                var Contraseña by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.align(CenterHorizontally).fillMaxWidth().height(67.dp),
                    shape = RoundedCornerShape(16.dp),
                    value = Contraseña,
                    onValueChange = { Contraseña = it },
                    label = { Text("Contraseña") }
                    )
            }
        }







    }
}






