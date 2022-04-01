package com.BFCAI.encryptionapp.Presentation.SendScreen

import com.BFCAI.encryptionapp.Domain.Model.ContactsModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data  class SendScreenState (
    val isloading:Boolean = false,
    val data :ContactsModel ? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)