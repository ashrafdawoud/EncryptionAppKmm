package com.BFCAI.encryptionapp.android.Presentation.Navigation.ContactScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenEvent
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenState
import com.BFCAI.encryptionapp.android.Presentation.Component.BottomNavigationBar
import com.BFCAI.encryptionapp.android.Presentation.Component.NoDataComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ContactScreen.Component.ContactsComponent
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Theme.AppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendScreen(
    navController: NavController,
    state :SendScreenState,
    event :(SendScreenEvent)->Unit
    ){
    val context = LocalContext.current
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
            SwipeRefresh(
                state = rememberSwipeRefreshState(state.isloading),
                onRefresh = {
                    event(SendScreenEvent.refreshListner)
                },
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = Color.White
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp,
                        bottom = 70.dp
                    )
                ) {
                    Text(
                        text = "Contacts",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Start,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp)
                            .background(
                                color = MaterialTheme.colors.surface,
                                shape = MaterialTheme.shapes.large
                            )
                            .height(45.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(Screens.SearchScreen.rout)
                                }
                            ),


                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 5.dp, end = 5.dp),
                            tint = Color.LightGray
                        )
                        Text(
                            text = "Search with email ..." ,
                            color = Color.LightGray,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    if (state.isloading && state.data == null) {

                    } else if (!state.isloading && state.data == null) {
                        NoDataComponent()
                    } else if (state.data != null){
                        if (state.data!!.results.isEmpty()){
                            NoDataComponent()
                        }else{
                            LazyColumn {
                                itemsIndexed(
                                    state.data!!.results
                                ) { index, item ->
                                    ContactsComponent(item = item, state = state, event = event , navController = navController)
                                }
                            }
                        }

                    }
                }
            }
        }
    }

}