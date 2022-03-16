package com.BFCAI.encryptionapp.Presentation.EncryptionScreen

import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent

sealed class EncryptionScreenEvents{
    data class choose_Type(val type:String ): EncryptionScreenEvents()
    data class choose_Encr(val enccy:String ): EncryptionScreenEvents()
    data class choose_file(val file: ByteArray,val filename :String,val filetype :String,val encryptionTool :String,val userid :String ): EncryptionScreenEvents()
    object upload_file : EncryptionScreenEvents()

}
