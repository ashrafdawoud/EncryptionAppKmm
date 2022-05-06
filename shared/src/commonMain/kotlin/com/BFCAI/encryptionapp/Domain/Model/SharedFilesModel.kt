package com.BFCAI.encryptionapp.Domain.Model

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.FileDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.FilesDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class SharedFilesModel(
    val results :List<SharedFilesItemModel>
)
data class SharedFilesItemModel (
    val objectId :String,
    val fileDto :SharedfileModel,
    val sender :SenderOrReciverModel,
    val reciver :SenderOrReciverModel,
)
data class SenderOrReciverModel(
    val objectId:String,
    val username:String,
    val email:String,
    )
data class SharedfileModel(
    val objectId:String,
    val file_type:String,
    val encryption_tool_id:String,
    val user_id:String,
    val file:FilesDto,
    )