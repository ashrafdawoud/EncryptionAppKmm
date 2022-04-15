package com.BFCAI.encryptionapp.Domain.Model

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.FileDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class SharedFilesModel(
    val results :List<SharedFilesItemModel>
)
data class SharedFilesItemModel (
    val objectId :String,
    val encryption_tool_id :String,
    val file_type :String,
    val file : FileDto,
    val sender :SenderOrReciverModel,
    val reciver :SenderOrReciverModel,
    val password :String,
)
data class SenderOrReciverModel(
    val objectId:String,
    val username:String,
    val emailVerified:Boolean,

    )