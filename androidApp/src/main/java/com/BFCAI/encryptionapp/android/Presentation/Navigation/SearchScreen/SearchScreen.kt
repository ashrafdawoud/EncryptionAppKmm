package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenState
import com.BFCAI.encryptionapp.android.Presentation.Component.NoDataComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component.SearchBar
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    //state : SearchScreenState,
    // event : SearchScreenEvent,
) {
    AppTheme(
        displayProgressBar = false,//state.isloading,
        onRemoveHeadMessage = { /*TODO*/ },
       // queue = state.queue
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    backgroundColor = MaterialTheme.colors.background,
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically)
                                .padding(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White,
                            )
                        }
                        SearchBar()
                    }
                }
            },
        ) {
            NoDataComponent()
        }
    }
}