package com.BFCAI.encryptionapp.Interactors.UserFileRepository

import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface UserFileRepository {
    suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String,token:String) : Flow<DataState<String>>
    suspend fun getAllFiles(userId:String,token:String) : Flow<DataState<UserFilesModel>>
    suspend fun deleteFile(objectId:String,token:String) : Flow<DataState<String>>

}