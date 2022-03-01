package com.BFCAI.encryptionapp.android.Presentation.Navigation.ProfileScreen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.android.Presentation.Component.BottomNavigationBar
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(navController: NavController){
    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            Text(text = "home screen")
        }
    }

}