package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R

@Composable
fun SearchBar() {
    var state by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .height(50.dp),

        ) {
        TextField(
            value = state,
            onValueChange = {
                state = it
            },
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.large
                ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(),
                    tint = Color.LightGray
                )
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

            )
    }
}