package com.BFCAI.encryptionapp.Presentation.SearchScreen

import com.BFCAI.encryptionapp.Domain.Model.ContactsCopyModel
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data class SearchScreenState(
    val isloading:Boolean = false,
    val data : ContactsCopyModel? = null,
    val contactAdded : Boolean = false,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)