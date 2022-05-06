package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ShareFileDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UserFilesDto
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel

interface UserFilesInterface {
    suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String,token:String) : String
    suspend fun getAllFiles(userid: String,token:String) : UserFilesModel
    suspend fun deleteFile(objectId:String,token:String) : String
    suspend fun shareFile(fileId:String,senderId:String,reciverid:String,token:String) : String
}