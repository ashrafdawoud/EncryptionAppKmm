package com.BFCAI.encryptionapp.android.Presentation.Component

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun CustomSnackBar(
    discription:String?
) {
    Toast.makeText(
        LocalContext.current,
        discription,
        Toast.LENGTH_SHORT
    ).show()
}