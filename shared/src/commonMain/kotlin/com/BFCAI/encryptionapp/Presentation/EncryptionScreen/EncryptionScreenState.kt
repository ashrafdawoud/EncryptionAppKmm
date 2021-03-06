package com.BFCAI.encryptionapp.Presentation.EncryptionScreen

import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue
import io.ktor.utils.io.core.*
import java.io.File

data class EncryptionScreenState(
    var isloading:Boolean = false,
    var filename : String = "no file selected yet",
    var fileType : String = "application/pdf",
    var fileBytes : ByteArray = "".toByteArray(),
    var encryType : String = "AES (Advanced Encryption System)",
    var userid : String?=null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
