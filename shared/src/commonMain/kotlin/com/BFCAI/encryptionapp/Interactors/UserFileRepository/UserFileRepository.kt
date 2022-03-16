package com.BFCAI.encryptionapp.Interactors.UserFileRepository

import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface UserFileRepository {
    suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String) : Flow<DataState<String>>
    suspend fun getAllFiles(userId:String) : Flow<DataState<UserFilesModel>>
    suspend fun deleteFile(objectId:String) : Flow<DataState<String>>

}