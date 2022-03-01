package com.BFCAI.encryptionapp.android.Presentation.Navigation.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.BFCAI.encryptionapp.android.Presentation.Component.BottomNavigationBar
import com.BFCAI.encryptionapp.android.Presentation.Navigation.HomeScreen.Component.CardComponent
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navController:NavController){
    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ }
    ) {
        Scaffold(
            topBar = {
                     TopAppBar(
                         modifier = Modifier.padding(start = 20.dp , end = 20.dp , top = 10.dp),
                         elevation = 0.dp,
                         backgroundColor = MaterialTheme.colors.background
                     ) {
                         Text(
                             text = "ENCO" ,
                             color = Color.White ,
                             style = MaterialTheme.typography.h2,
                         )
                     }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            CardComponent(navController = navController)
        }
    }

}