package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas
import com.alixmontesinos.app_simmer.ui.theme.ItimFont
import kotlinx.coroutines.delay

@Composable
fun SplashScreen2(navController: NavHostController){

    LaunchedEffect(key1 = true){
        delay(1500)
        navController.popBackStack()
        
        val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        if (user != null) {
            navController.navigate(OtrasRutas.Home.route)
        } else {
            navController.navigate(OtrasRutas.Welcome.route)
        }
    }
    SplashScreen()
}
@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Fondo de la pantalla de carga",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "simmer",
            fontFamily = ItimFont,
            fontSize = 70.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp), // Puedes ajustar esta distancia
            color = Color.Black
        )

        /*val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo_animation  ))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(600.dp) // Ajusta la altura de la animación
        )*/ // Animacion del wey bailando

        Image(
            // Carga la imagen desde R.drawable.nosisino
            painter = painterResource(id = R.drawable.user_avatar),
            // Descripción para accesibilidad
            contentDescription = "Logo de Simmer",
            // Escala para llenar el espacio y cortar si es necesario
            contentScale = ContentScale.Crop,
            // Aplica los modificadores de alineación y tamaño
            modifier = Modifier
                .align  (Alignment.BottomCenter)
                .offset(0.dp,-  200.dp)
                .width(400.dp)
                .height(400.dp) // Ajusta la altura de la imagen
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
