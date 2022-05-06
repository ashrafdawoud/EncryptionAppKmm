package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.BFCAI.encryptionapp.android.EncryptionAlgorisms.AEScipherEncrypt
import com.BFCAI.encryptionapp.android.EncryptionAlgorisms.BLOWFISHcipherEncrypt
import com.BFCAI.encryptionapp.android.EncryptionAlgorisms.DESedecipherEncrypt
import com.BFCAI.encryptionapp.android.EncryptionAlgorisms.DetectEncryptionAlgorism
import io.ktor.http.*
import java.io.File


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun UploadFileButton(
    state: EncryptionScreenState,
    onTriggerEvent: (EncryptionScreenEvents) -> Unit,
    uploadfiles: (NavController) -> Unit
) {
    val context = LocalContext.current
    var uri by remember { mutableStateOf("") }
    var extention by remember { mutableStateOf("pdf") }
    var filetype by remember { mutableStateOf("pdf") }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { result ->
            result?.let {

                uri = it.getPath().toString()
                val item = context.contentResolver.openInputStream(it)
                val bytes = item?.readBytes()
                onTriggerEvent(
                    EncryptionScreenEvents.choose_file(
                        bytes!!,
                        extention,
                        filetype = filetype,
                        encryptionTool = state.encryType,
                        userid = context.getSharedPreferences(PublicData.GENERAL_PREF, AppCompatActivity.MODE_PRIVATE).getString("User_Id","")!!
                    )
                )
                item?.close()
            }
        }

    Column {
        Button(
            onClick = {
                var type: String = when (state.fileType) {
                    "application/pdf" -> {
                        "application/pdf"
                    }
                    "audio/mp3" -> {
                        ContentType.Audio.MPEG.toString()
                    }
                    "image/jpeg" -> {
                        "image/jpeg"
                    }
                    "video/mp4" -> {
                        "video/mp4"
                    }
                    else -> {
                        "application/pdf"
                    }
                }
                when (state.fileType) {
                    "application/pdf" -> {
                        extention = "pdf"
                    }
                    "audio/mp3" -> {
                        extention = "mp3"
                    }
                    "image/jpeg" -> {
                        extention = "jpeg"
                    }
                    "video/mp4" -> {
                        extention = "mp4"
                    }
                    else -> {
                        extention = "pdf"
                    }
                }
                launcher.launch(type)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                )
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Choose file",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 40.dp,
                    end = 40.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )

            )
        }


    }

}
