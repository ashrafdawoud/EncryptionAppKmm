package com.BFCAI.encryptionapp.Presentation.LoginScreen

import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data class LoginScreenState(
    var isloading:Boolean = false,
    var data : UserModel ? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
