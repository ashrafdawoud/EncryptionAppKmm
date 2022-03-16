package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserFilesDto
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel

interface UserFilesInterface {
    suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String) : String
    suspend fun getAllFiles(userid: String) : UserFilesModel
    suspend fun deleteFile(objectId:String) : String
}