package com.BFCAI.encryptionapp.Presentation.LoginScreen

sealed class LoginScreenEvent(){
   data class login(val email:String , val password:String , val openHomeScreen:()-> Unit):LoginScreenEvent()
}
