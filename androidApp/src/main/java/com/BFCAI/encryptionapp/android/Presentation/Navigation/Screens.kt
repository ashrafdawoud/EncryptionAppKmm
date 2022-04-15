package com.BFCAI.encryptionapp.android.Presentation.Navigation

import android.annotation.SuppressLint
import com.BFCAI.encryptionapp.android.R

sealed class Screens (
    val rout:String,
    var icon: Int?,
    var title: String
        ) {
    object SplashScreen : Screens("SplashScreen",null,"SplashScreen")
    object LoginScreen : Screens("LoginScreen",null,"LoginScreen")
    object SignUpScreen : Screens("SignUpScreen",null,"SignUpScreen")
    object HomeScreen : Screens("HomeScreen",R.drawable.ic_home,"Home")
    object MyFilesScreen : Screens("MyFilesScreen",R.drawable.ic_logo,"MyFiles")
    object SentFilesScreen : Screens("SentFilesScreen",R.drawable.ic_logo,"SentFiles")
    object SendScreen : Screens("SendScreen",R.drawable.ic_send,"Send")
    object ProfileScreen : Screens("ProfileScreen",R.drawable.ic_account,"Profile")
    object EncryptionScreen : Screens("EncryptionScreen",R.drawable.ic_account,"EncryptionScreen")
}