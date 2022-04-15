package com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.Component

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SingleContactDto
import com.BFCAI.encryptionapp.Domain.Model.SingleContactModel
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Presentation.SearchScreen.SearchScreenEvent
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenEvent
import com.BFCAI.encryptionapp.android.R

@Composable
fun SearchListCard(
    item : SingleContactModel,
    event: (SearchScreenEvent)->Unit
){
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 4.dp)
            .clickable(
                onClick = {

                }
            ),
        verticalAlignment = Alignment.CenterVertically,


        ) {
        Icon(
            modifier = Modifier
                .padding(start = 7.dp, end = 7.dp)
                .size(30.dp),
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = null,
            tint = Color.White,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(5.dp),

            ) {
            Text(
                text = item.username,
                style = MaterialTheme.typography.h4,
                color = Color.White,
                modifier = Modifier.padding(start = 2.dp, end = 5.dp, bottom = 2.dp),
            )
            Text(
                text = "${item.emailCopy}",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                modifier = Modifier.padding(start = 2.dp, end = 5.dp, top = 2.dp),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_add_box_24),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .width(25.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        event(SearchScreenEvent.AddContact(
                            context.getSharedPreferences(
                                PublicData.GENERAL_PREF,
                                AppCompatActivity.MODE_PRIVATE
                            ).getString("user_token", "")!!,
                            item.objectId
                        ))
                    }
                )
        )
    }
}