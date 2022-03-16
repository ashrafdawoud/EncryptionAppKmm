package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.SharedFilesDto
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel

interface SharedFilesInterface {
    suspend fun getAllFiles(senderId:String): SharedFilesModel
    suspend fun deleteFiles(ObjectId:String):String
}