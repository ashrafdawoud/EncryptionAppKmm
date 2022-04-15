package com.BFCAI.encryptionapp.Presentation.SharedFilesScreen

import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent

sealed class SharedFilesEvent(){
    data class cardIsClicked(val cardClicked:Boolean):SharedFilesEvent()
    data class refreshListner(val userId:String):SharedFilesEvent()
    data class deleteFile(val objectId:String):SharedFilesEvent()
}
