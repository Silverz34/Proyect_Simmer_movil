package com.alixmontesinos.app_simmer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R
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


        Card(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(500.dp)
            .padding(0.dp),
            shape = RoundedCornerShape(50.dp),){

            Column (modifier = Modifier.padding(horizontal = 40.dp, vertical = 50.dp)){
                Text( text= "Welcome!", fontSize = 30.sp)

            }
        }
    }

}