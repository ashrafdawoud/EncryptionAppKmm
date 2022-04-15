package com.BFCAI.encryptionapp.Presentation.UserFilesScreen

import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent

sealed class UserFilesEvent(){
    data class cardIsClicked(val cardClicked:Boolean):UserFilesEvent()
    data class refreshListner(val userId:String):UserFilesEvent()
    data class deleteFile(val objectId:String):UserFilesEvent()
}
