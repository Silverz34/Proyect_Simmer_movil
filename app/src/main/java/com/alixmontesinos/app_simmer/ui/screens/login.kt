package com.alixmontesinos.app_simmer.ui.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import com.alixmontesinos.app_simmer.ui.theme.NunitoSans
import com.alixmontesinos.app_simmer.ui.theme.YellowT


@Preview
@Composable
fun Login(){



    Box(modifier = Modifier.fillMaxSize()){


        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla ",
            modifier = Modifier.fillMaxWidth().height(550.dp),
            contentScale = ContentScale.Crop
        )


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.align(Alignment.TopCenter)
                    .height(240.dp)
                    .offset(0.dp,160.dp)
                ,
                contentScale = ContentScale.Crop
            )


        Card(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(500.dp)
            .padding(0.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp),

            colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)

            ){

            Column (modifier = Modifier.padding(horizontal = 40.dp, vertical = 50.dp)){
                Text( text= "Bienvenido!",
                    fontSize = 48.sp,
                    fontFamily = ItimFont
                )

                Spacer(modifier = Modifier.height(45.dp))

                Text("Somos una comunidad muy activa y solidaria, conócenos! ",
                    fontSize = 26.sp,
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black

                    )

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = { Log.d("Simmer", "Botón Iniciar Sesión presionado") },
                    modifier = Modifier.width(230.dp).height(60.dp).align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),

                ) {
                    Text(text= "Iniciar sesión",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = YellowT
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { Log.d("Simmer", "Botón Iniciar Sesión presionado") },
                    modifier = Modifier.width(230.dp).height(60.dp).align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                            containerColor = YellowT,

                    )
                ) {
                    Text(text= "Registrate",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black)
                }


            }
        }
    }

}