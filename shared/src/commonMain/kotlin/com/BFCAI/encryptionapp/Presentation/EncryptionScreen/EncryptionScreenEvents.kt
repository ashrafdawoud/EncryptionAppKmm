package com.BFCAI.encryptionapp.Presentation.EncryptionScreen

import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent

sealed class EncryptionScreenEvents{
    data class choose_Type(val type:String ): EncryptionScreenEvents()
    data class choose_file( val byteArray: ByteArray, val filename:String ): EncryptionScreenEvents()
    data class upload_file(val file:String ): EncryptionScreenEvents()

}
