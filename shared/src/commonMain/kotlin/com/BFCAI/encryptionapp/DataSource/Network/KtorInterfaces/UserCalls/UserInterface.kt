package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls

import com.BFCAI.encryptionapp.Domain.Model.UserModel

interface UserInterface {
    suspend fun login(email : String , password : String):UserModel
}