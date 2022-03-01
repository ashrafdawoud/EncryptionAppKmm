package com.BFCAI.encryptionapp.android.Presentation.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.semantics.Role.Companion.Button
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AccessFilePermissions (){
    // Camera permission state
    val filePermissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    val filePermissionState2 = rememberPermissionState(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    when (filePermissionState.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {

        }
        is PermissionStatus.Denied -> {
            LaunchedEffect(Unit){
                filePermissionState.launchPermissionRequest()
            }
        }
    }
    when (filePermissionState2.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {

        }
        is PermissionStatus.Denied -> {
            LaunchedEffect(Unit){
                filePermissionState2.launchPermissionRequest()
            }
        }
    }
}