package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components

import android.R.attr.data
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import io.ktor.http.*
import java.io.ByteArrayOutputStream
import java.io.File


@Composable
fun UploadFileButton(
    state: EncryptionScreenState,
    onTriggerEvent:(EncryptionScreenEvents)->Unit,
    uploadfiles:(ByteArray)->Unit
){
    val context = LocalContext.current
    var uri by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { result ->
       /* val uri = result.data?.data.toString()
        if(uri !== null) {
            val file = File(uri)
            var filename = file.absoluteFile.name
            onTriggerEvent(EncryptionScreenEvents.choose_file(filename))
            uploadfiles(file)
            Log.e("filename",filename.substring(filename.lastIndexOf("/")+1))
        }*/
        result?.let {

            uri = it.getPath().toString()
            val item = context.contentResolver.openInputStream(it)
            val bytes = item?.readBytes()

            val bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), it)

            val stream = ByteArrayOutputStream()
            // Compress image to lower quality scale 1 - 100
            // Compress image to lower quality scale 1 - 100
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = stream.toByteArray()


            onTriggerEvent(EncryptionScreenEvents.choose_file(bytes!! , File(it.getPath()).extension))
            item?.close()
        }

    }
    Column {
        Button(
            onClick = {
                var type :String = when(state.fileType){
                    "pdf"->{
                        ContentType.Application.Any.toString()
                    }
                    "audio" ->{
                        ContentType.Audio.MPEG.toString()
                    }
                    "image" ->{
                        ContentType.Image.Any.toString()
                    }
                    "video" ->{
                        ContentType.Video.MP4.toString()
                    }
                    else ->{
                        "application/pdf"
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
        val request = ImageRequest.Builder(context)
            .data(uri)
            .target(
                onStart = { placeholder ->
                    // Handle the placeholder drawable.
                },
                onSuccess = { result ->
                    // Handle the successful result.
                    Log.e("coil_image_suc", result.toString())
                },
                onError = { error ->
                    Log.e("coil_image_err", error.toString())
                }
            )
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
        imageLoader.enqueue(request)
        Image(
            painter = rememberImagePainter (
                Uri.parse(uri)
                //data  = Uri.parse(uri)  // or ht
            )
            ,
            contentDescription = "123",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }

}