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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.android.Presentation.Component.AccessFilePermissions
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components.EncryptionTypeMenu
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components.FileTypeMenu
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components.UploadFileButton
import com.example.food1fork.android.Presentation.Theme.AppTheme
import io.ktor.http.*
import java.io.File

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EncryptionScreen(
    navController: NavController,
    state:EncryptionScreenState,
    onTriggerEvent:(EncryptionScreenEvents)->Unit,
    uploadfiles:()->Unit
    ) {
    val fileTypes = listOf("pdf", "audio/mp3", "video/mp4", "image/jpeg")
    val encryptTypes = listOf(
        "AES/CBC/NoPadding",
        "AES/CBC/PKCS7Padding",
        "AES/CTR/NoPadding",
        "AES/ECB/NoPadding",
        "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"
    )

    var fileTypesPeriodExpanded by remember { mutableStateOf(false) }
    var encryptTypesPeriodExpanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    var selectedIndex2 by remember { mutableStateOf(0) }
    AppTheme(
        displayProgressBar = state.isloading,
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
                        menuItems = fileTypes,
                        menuExpandedState = fileTypesPeriodExpanded,
                        seletedIndex = selectedIndex,
                        updateMenuExpandStatus = {
                            fileTypesPeriodExpanded = true
                        },
                        onDismissMenuView = {
                            fileTypesPeriodExpanded = false
                        },
                        onMenuItemclick = { index ->
                            selectedIndex = index
                            fileTypesPeriodExpanded = false
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
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Text(
                        text = "You Should Choose Key Type We Will Use It To Encrypt Files You Choose \n" +
                                "note : \n" +
                                "these Files Will Be Encrypted in Server Too Not on Application Only",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    EncryptionTypeMenu(
                        state = state,
                        onTriggerEvent = onTriggerEvent,
                        menuItems = encryptTypes,
                        menuExpandedState = encryptTypesPeriodExpanded,
                        seletedIndex = selectedIndex2,
                        updateMenuExpandStatus = {
                            encryptTypesPeriodExpanded = true
                        },
                        onDismissMenuView = {
                            encryptTypesPeriodExpanded = false
                        },
                        onMenuItemclick = { index ->
                            selectedIndex2 = index
                            encryptTypesPeriodExpanded = false
                        }
                    )
                    Button(
                        onClick = {
                            onTriggerEvent(EncryptionScreenEvents.upload_file)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.background,
                                shape = MaterialTheme.shapes.large
                            )
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Encrypt&Upload File",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                start = 40.dp,
                                end = 40.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            )

                        )
                    }
                }


            }
        )
    }
}