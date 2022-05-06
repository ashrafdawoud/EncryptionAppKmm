package com.BFCAI.encryptionapp.Presentation.UserFilesScreen

import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data class UserFilesState(
    val isloading:Boolean = false,
    val data :UserFilesModel? = null,
    val uploaded :String? = null,
    val cardisClicked:Boolean = false,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
