package com.BFCAI.encryptionapp.Interactors.SharedFilesRepository

import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface SharedFilesReposioty {
    suspend fun getAllFiles(senderId:String,token:String): Flow<DataState<SharedFilesModel>>
    suspend fun deleteFiles(objectId:String,token:String):Flow<DataState<String>>
}