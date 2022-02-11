package com.BFCAI.encryptionapp.android.Presentation.Navigation.SplashScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Theme.AppThemeNoArg
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SplashScreen(){
 var rotate by remember { mutableStateOf(0f) }
    LaunchedEffect(Unit){
        while (rotate <= 360f){
            if (rotate == 360f)
                rotate = 10f
            delay(200)
            rotate = rotate+10f

        }
    }
    AppThemeNoArg {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Image(
                painter = painterResource(id = R.drawable.ic_logo) ,
                contentDescription = "Logo",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .rotate(rotate),

                )
            Text(
                text = "ENCO",
                color = Color.White,
                style = MaterialTheme.typography.h2,
                fontSize = 30.sp,
                modifier = Modifier.padding(15.dp)
            )
            LinearProgressIndicator(
                color = MaterialTheme.colors.primary,
                backgroundColor = Color.White,
                modifier = Modifier.padding(top=40.dp)
            )

        }
    }



}