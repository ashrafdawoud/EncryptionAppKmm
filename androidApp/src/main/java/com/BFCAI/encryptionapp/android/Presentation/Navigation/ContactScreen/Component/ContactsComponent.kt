package com.BFCAI.encryptionapp.android.Presentation.Navigation.ContactScreen.Component

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Model.ContactsItemModel
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenEvent
import com.BFCAI.encryptionapp.Presentation.SendScreen.SendScreenState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R
@Composable
fun ContactsComponent(
    item: ContactsItemModel,
    state: SendScreenState,
    event: (SendScreenEvent) -> Unit,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 5.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .clickable(
                onClick = {
                    navController.navigate(Screens.ShareFileScreen.rout+"/"+item.contact2.objectId)
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
                text = item.contact2.username,
                style = MaterialTheme.typography.h4,
                color = Color.White,
                modifier = Modifier.padding(start = 2.dp, end = 5.dp, bottom = 2.dp),
            )
            Text(
                text = "Added at : ${item.updatedAt}",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                modifier = Modifier.padding(start = 2.dp, end = 5.dp, top = 2.dp),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_delete_svgrepo_com),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .width(25.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        event(SendScreenEvent.deleteContact(item.objectId))
                    }
                )
        )
    }
}