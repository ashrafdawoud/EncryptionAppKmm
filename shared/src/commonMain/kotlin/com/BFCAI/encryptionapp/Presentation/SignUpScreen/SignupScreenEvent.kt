package com.BFCAI.encryptionapp.Presentation.SignUpScreen

sealed class SignupScreenEvent {
    data class signup(val email:String , val password:String , val username:String , val openLoginScreen:()-> Unit):SignupScreenEvent()
}