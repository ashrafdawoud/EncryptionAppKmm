package com.BFCAI.encryptionapp.android.Presentation.Navigation.HomeScreen.Component

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R

@Composable
fun CardComponent(navController: NavController) {
    val interactionSource1 = remember { MutableInteractionSource() }
    val interactionSource2 = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.large
                )
                .fillMaxWidth()
                .clickable(
                    enabled = true,
                    interactionSource = interactionSource1,
                    indication = LocalIndication.current,
                    onClick = {
                        navController.navigate(Screens.EncryptionScreen.rout)
                    }
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 15.dp)
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = Color.White,

                    )
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Encryption",
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 2.dp),
                    )
                    Text(
                        text = "Encrypt files ( file , audio , video ) ",
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 2.dp),
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.large
                )
                .fillMaxWidth()
                .clickable(
                    enabled = true,
                    interactionSource = interactionSource2,
                    indication = LocalIndication.current,
                    onClick = { }
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = Color.White,

                    )
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Decryption",
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 2.dp),
                    )
                    Text(
                        text = "Decrypt files ( file , audio , video ) ",
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 2.dp),
                    )
                }
            }
        }
    }
}