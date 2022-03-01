package com.BFCAI.encryptionapp.Interactors.UserReposetories

import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login (email: String, password: String) : Flow<DataState<UserModel>>
    suspend fun signUp (email: String, password: String , username:String) :Flow<DataState<String>>
    suspend fun ActivateEmail (email: String) :Flow<DataState<String>>

}