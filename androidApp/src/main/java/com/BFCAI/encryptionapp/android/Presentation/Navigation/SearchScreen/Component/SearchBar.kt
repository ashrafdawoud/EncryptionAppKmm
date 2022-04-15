package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenEvent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    event :(SearchScreenEvent)->Unit,
    navController: NavController
) {
    val context = LocalContext.current
    var state by remember { mutableStateOf("") }
    val inputService = LocalTextInputService.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, top = 10.dp, bottom = 10.dp, start = 10.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .height(70.dp),

        ) {
        TextField(
            value = state,
            onValueChange = {
                state = it
                event
            },
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.large
                )
                .focusRequester(focusRequester),
            leadingIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector =  Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(),
                        tint = Color.LightGray
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { state = "" }) {
                    Icon(
                        imageVector =  Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(),
                        tint = Color.LightGray
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                focusedIndicatorColor = MaterialTheme.colors.background,
                unfocusedIndicatorColor = MaterialTheme.colors.background,
                textColor = Color.White
            ),
            placeholder = {
                Text(
                    text = "Search with email ...",
                    color = Color.Gray,
                    style = MaterialTheme.typography.h6
                )
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.h6,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (!state.isEmpty()) {
                        inputService?.hideSoftwareKeyboard()
                        focusManager.clearFocus()
                        event(
                            SearchScreenEvent.Search(
                                token = context.getSharedPreferences(
                                    PublicData.GENERAL_PREF,
                                    AppCompatActivity.MODE_PRIVATE
                                ).getString("user_token", "")!!, email = state
                            )
                        )
                    }else{
                        Toast.makeText(context , "search is empty" , Toast.LENGTH_SHORT).show()
                    }
                }
            )
            )
    }
    LaunchedEffect("") {
        delay(300)
        inputService?.showSoftwareKeyboard()
        focusRequester.requestFocus()
    }
}