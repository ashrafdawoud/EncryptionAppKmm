package com.BFCAI.encryptionapp.android.Presentation.Navigation.ShareFileScreen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShareFileScreen(
    navController: NavController,
    state:UserFilesState,
    event: (UserFilesEvent) ->Unit,
    reciverId:String?
){
    AppTheme(
        displayProgressBar = state.isloading,
        onRemoveHeadMessage = { /*TODO*/ },
        queue = state.queue
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.background,
                    title = {
                        Text(
                            text = "Files",
                            style = MaterialTheme.typography.h3,
                            color = Color.White
                        )
                    },
                    navigationIcon = if (navController.previousBackStackEntry != null) {
                        {
                            IconButton(
                                onClick = { navController.navigateUp() },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(top = 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White,
                                )
                            }
                        }
                    } else {
                        null
                    }

                )
            },
            content = {
                if (state.uploaded!=null){
                    navController.navigate(Screens.SuccessScreen.rout+"/"+ Screens.ShareFileScreen.title)
                    event(UserFilesEvent.resetState)
                }
                ShareFileListComponent(state = state ,event = event , reciverId)
            }
        )
    }
}