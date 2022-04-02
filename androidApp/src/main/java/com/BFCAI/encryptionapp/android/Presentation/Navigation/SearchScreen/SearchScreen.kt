package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenEvent
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenState
import com.BFCAI.encryptionapp.android.Presentation.Component.NoDataComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.Component.UserFileCard
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component.SearchBar
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component.SearchListCard
import com.example.food1fork.android.Presentation.Theme.AppTheme

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchScreenState,
    event: (SearchScreenEvent) -> Unit
) {
    AppTheme(
        displayProgressBar = state.isloading,
        onRemoveHeadMessage = { /*TODO*/ },
        queue = state.queue
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    backgroundColor = MaterialTheme.colors.background,
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SearchBar(event, navController)
                    }
                }
            },
        ) {
            if (!state.isloading && state.data == null) {
                NoDataComponent()
            } else if (state.data != null) {
                if (state.contactAdded){
                    navController.navigate(Screens.SuccessScreen.rout+"/"+Screens.SearchScreen.title)
                }
                if (!state.data!!.results.isEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            state.data!!.results
                        ) { index, item ->
                            SearchListCard(item = item , event = event)
                        }
                    }
                }else{
                    NoDataComponent()
                }
            }

        }
    }
}