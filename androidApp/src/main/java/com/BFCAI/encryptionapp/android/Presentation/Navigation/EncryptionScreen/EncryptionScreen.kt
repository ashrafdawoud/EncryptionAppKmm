package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.android.Presentation.Component.AccessFilePermissions
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components.FileTypeMenu
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components.UploadFileButton
import com.example.food1fork.android.Presentation.Theme.AppTheme
import java.io.File

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EncryptionScreen(
    navController: NavController,
    state:EncryptionScreenState,
    onTriggerEvent:(EncryptionScreenEvents)->Unit,
    uploadfiles:(ByteArray)->Unit
    ) {
    val billingPeriodItems = listOf("pdf", "audio", "video", "image")

    var billingPeriodExpanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.background,
                    title = {
                        Text(
                            text = "Encrypt Files",
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    FileTypeMenu(
                        state = state,
                        onTriggerEvent = onTriggerEvent,
                        menuItems = billingPeriodItems,
                        menuExpandedState = billingPeriodExpanded,
                        seletedIndex = selectedIndex,
                        updateMenuExpandStatus = {
                            billingPeriodExpanded = true
                        },
                        onDismissMenuView = {
                            billingPeriodExpanded = false
                        },
                        onMenuItemclick = { index ->
                            selectedIndex = index
                            billingPeriodExpanded = false
                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart)
                            .padding(top = 20.dp)
                            .background(
                                color = MaterialTheme.colors.onBackground,
                                shape = MaterialTheme.shapes.large
                            )
                            .border(0.5.dp, Color.DarkGray, shape = MaterialTheme.shapes.large)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = state.filename,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    AccessFilePermissions()
                    UploadFileButton(
                        state = state,
                        onTriggerEvent = onTriggerEvent,
                        uploadfiles = uploadfiles
                        )

                }


            }
        )
    }
}