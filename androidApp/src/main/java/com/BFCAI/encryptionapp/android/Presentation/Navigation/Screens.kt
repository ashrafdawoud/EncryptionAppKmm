package com.BFCAI.encryptionapp.android.Presentation.Navigation

sealed class Screens (
    val rout:String
        ) {
    object SplashScreen : Screens("SplashScreen")
    object LoginScreen : Screens("LoginScreen")
    object HomeScreen : Screens("HomeScreen")
    object SignUpScreen : Screens("SignUpScreen")
}