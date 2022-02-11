package com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.Components.TextFieldComponent
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Theme.AppTheme
import com.example.food1fork.android.Presentation.Theme.AppThemeNoArg
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginScreenState,
    onTriggerEvent:(LoginScreenEvent)->Unit,
    loginSuccess:()->Unit,
    signUpClick:()->Unit,
    ) {
    AppTheme(
        displayProgressBar = state.isloading,
        queue = state.queue,
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
            TextFieldComponent(onTriggerEvent,loginSuccess,signUpClick)
        }
    }
}