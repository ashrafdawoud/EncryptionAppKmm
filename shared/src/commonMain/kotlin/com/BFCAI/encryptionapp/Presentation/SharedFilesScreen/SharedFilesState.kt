package com.BFCAI.encryptionapp.Presentation.SharedFilesScreen

import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data class SharedFilesState (
    val isloading:Boolean = false,
    val data : SharedFilesModel? = null,
    val cardisClicked:Boolean = false,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)