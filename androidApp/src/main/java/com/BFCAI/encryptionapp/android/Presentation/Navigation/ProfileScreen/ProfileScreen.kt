package com.BFCAI.encryptionapp.android.Presentation.Navigation.ProfileScreen

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenState
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesState
import com.BFCAI.encryptionapp.android.Presentation.Component.BottomNavigationBar
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    sendState:SendScreenState,
    userState:UserFilesState
    ) {
    val context = LocalContext.current
    val name = context.getSharedPreferences(PublicData.GENERAL_PREF, AppCompatActivity.MODE_PRIVATE).getString("User_Name", "NoName")!!
    val email = context.getSharedPreferences(PublicData.GENERAL_PREF, AppCompatActivity.MODE_PRIVATE).getString("User_Email", "NoEmail")!!
    AppTheme(
        displayProgressBar = false,
        onRemoveHeadMessage = { /*TODO*/ }
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
                    text = "Profile",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start,
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
                        text =name,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
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
                        text = email,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
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
                        text = "Contacts Number : ${sendState.data?.results?.size}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
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
                        text = "Encrypted Files Number : ${userState.data?.results?.size}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Button(
                    onClick = {
                        context.getSharedPreferences(PublicData.GENERAL_PREF, AppCompatActivity.MODE_PRIVATE).edit().clear().commit()
                        navController.navigate(Screens.LoginScreen.rout)
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
                        text = "Logout",
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
    }

}