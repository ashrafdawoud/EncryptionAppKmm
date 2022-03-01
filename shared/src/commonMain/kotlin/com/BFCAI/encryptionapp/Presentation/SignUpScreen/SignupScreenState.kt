package com.BFCAI.encryptionapp.Presentation.SignUpScreen

import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

data class SignupScreenState(
    var isloading:Boolean = false,
    var data : String ? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)
