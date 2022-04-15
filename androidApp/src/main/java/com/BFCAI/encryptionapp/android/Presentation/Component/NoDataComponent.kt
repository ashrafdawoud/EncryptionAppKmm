package com.BFCAI.encryptionapp.android.Presentation.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R

@Composable
fun NoDataComponent(){
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_database_server_svgrepo_com),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .width(150.dp)
        )
        Text(
            text = "No Data Found To Display" ,
            color = Color.White ,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp , end = 20.dp , top = 30.dp),
            textAlign = TextAlign.Center

        )
    }
}