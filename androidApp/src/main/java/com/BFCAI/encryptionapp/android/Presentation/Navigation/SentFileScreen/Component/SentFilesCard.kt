package com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen.Component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesItemModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesItemModel
import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.Component.IconItem
import com.BFCAI.encryptionapp.android.R
import com.BFCAI.encryptionapp.android.Utils.DownloadFiles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserFileCard(
    item: SharedFilesItemModel,
    event: (SharedFilesEvent) -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large
            )
            .fillMaxWidth()
            .clickable(
                onClick = {
                    event(SharedFilesEvent.cardIsClicked(true))
                    coroutineScope.launch(Dispatchers.IO) {
                        val file = DownloadFiles.DownloadSentFiles(item, context, event)
                        Log.e("filename", file?.name.toString())
                    }
                }
            ),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,


            ) {
            IconItem(fileType = item.file_type)
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(5.dp),

                ) {
                Text(
                    text = item.file_type,
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 2.dp),
                )
                Text(
                    text = "Sent by : ${item.sender.username}",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 2.dp),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_delete_svgrepo_com),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .width(25.dp)
                    .clickable (
                        enabled = true,
                        onClick = {
                            event(SharedFilesEvent.deleteFile(item.objectId))
                        }
                    )
            )
        }
    }
}