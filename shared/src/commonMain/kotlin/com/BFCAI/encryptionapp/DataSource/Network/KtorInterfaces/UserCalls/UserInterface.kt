package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls

import com.BFCAI.encryptionapp.Domain.Model.UserModel
import io.ktor.http.cio.*

interface UserInterface {
    suspend fun login(email : String , password : String):UserModel
    suspend fun Signup(email : String , password : String , username :String):String
    suspend fun ActivateEmail(email : String):String
}