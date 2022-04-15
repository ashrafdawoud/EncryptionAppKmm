package com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.Component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.android.R

@Composable
fun IconItem(
    fileType:String
){
    when (fileType) {
        "application/pdf" -> {
            Icon(
                modifier = Modifier
                    .padding(start = 7.dp, end = 7.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_baseline_picture_as_pdf_24),
                contentDescription = null,
                tint = Color.White,
            )        }
        "audio/mp3" -> {
            Icon(
                modifier = Modifier
                    .padding(start = 7.dp, end = 7.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_round_audio_file_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
        "image/jpeg" -> {
            Icon(
                modifier = Modifier
                    .padding(start = 7.dp, end = 7.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_round_image_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
        "video/mp4" -> {
            Icon(
                modifier = Modifier
                    .padding(start = 7.dp, end = 7.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_round_video_library_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
        else -> {
            Icon(
                modifier = Modifier
                    .padding(start = 7.dp, end = 7.dp)
                    .size(30.dp),
                painter = painterResource(id = R.drawable.ic_baseline_picture_as_pdf_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}