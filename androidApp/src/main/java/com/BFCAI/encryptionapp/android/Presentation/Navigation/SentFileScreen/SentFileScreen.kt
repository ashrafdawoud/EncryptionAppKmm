package com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesEvent
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesState
import com.BFCAI.encryptionapp.android.Presentation.Component.BottomNavigationBar
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.Component.FilesListComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen.Component.SentFilesListComponent
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SentFilesScreen(
    navController: NavController,
    state:SharedFilesState,
    event:(SharedFilesEvent)->Unit
    ){
    AppTheme(
        displayProgressBar = state.isloading,
        onRemoveHeadMessage = { /*TODO*/ },
        queue = state.queue
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 70.dp)
            ) {
                Text(
                    text = "Sent Files",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start,
                )
                SentFilesListComponent(
                    state = state,
                    event = event
                )
            }
        }
    }

}