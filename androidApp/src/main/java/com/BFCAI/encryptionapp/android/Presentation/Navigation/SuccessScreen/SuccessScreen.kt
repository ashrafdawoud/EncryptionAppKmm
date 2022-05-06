package com.BFCAI.encryptionapp.android.Presentation.Navigation.SuccessScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Theme.AppTheme
import java.util.*

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuccessScreen(
    navController: NavController,
    screenName: String
) {
    var discriptionstate by remember { mutableStateOf("Success on Encrypting and Uploading Files To our Secure Servers") }
    val discription: List<String> = Arrays.asList(
        "Success on Encrypting and Uploading Files To our Secure Servers",
        "Success on Add Contact to your Contacts List",
        "Success on Add File to your Contact",
    )

    if (screenName.equals(Screens.EncryptionScreen.title)) {
        discriptionstate = discription.get(0)
    } else if (screenName.equals(Screens.SearchScreen.title)) {
        discriptionstate = discription.get(1)
    }
    else if (screenName.equals(Screens.ShareFileScreen.title)) {
        discriptionstate = discription.get(2)
    }
    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ })
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_success_34),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .width(180.dp)
            )
            Text(
                text = discriptionstate,
                color = Color.White,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                textAlign = TextAlign.Center

            )
            Button(
                onClick = {
                    if (screenName.equals(Screens.EncryptionScreen.title)) {
                        navController.navigate(Screens.HomeScreen.rout)
                    } else if (screenName.equals(Screens.SearchScreen.title)) {
                        navController.navigate(Screens.SendScreen.rout)
                    }
                    else if (screenName.equals(Screens.ShareFileScreen.title)) {
                        navController.navigate(Screens.SendScreen.rout)
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = MaterialTheme.shapes.medium
                    )
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 40.dp)
            ) {
                Text(
                    text = "Close",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 40.dp,
                        end = 40.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                    color = MaterialTheme.colors.background

                )
            }

        }

    }
}