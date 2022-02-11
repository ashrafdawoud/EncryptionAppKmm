package com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.Components.TextFieldComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.Components.FormComponents
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(

){

    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ })
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp)
            )
            FormComponents()
        }
    }
}