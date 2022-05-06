package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import com.BFCAI.encryptionapp.Domain.Model.FilesModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedFilesDto (
    @SerialName("results")
    val results :List<SharedFilesItemDto>
        )
@Serializable
data class SharedFilesItemDto (
    @SerialName("objectId")
    val objectId :String,
    @SerialName("fileDto")
    val fileDto :SharedfileDto,
    @SerialName("sender")
    val sender :SenderOrReciverDto,
    @SerialName("reciver")
    val reciver :SenderOrReciverDto,
)
@Serializable
data class SenderOrReciverDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("username")
    val username:String,
    @SerialName("emailCopy")
    val email:String,
)
@Serializable
data class SharedfileDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("file_type")
    val file_type:String,
    @SerialName("encryption_tool_id")
    val encryption_tool_id:String,
    @SerialName("user_id")
    val user_id:String,
    @SerialName("file")
    val file: FilesDto,
)