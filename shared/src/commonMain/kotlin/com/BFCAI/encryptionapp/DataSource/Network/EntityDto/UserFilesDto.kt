package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFilesDto (
    @SerialName("results")
    val results :List<UserFilesItemDto>
        )
@Serializable
data class UserFilesItemDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("file_type")
    val file_type:String,
    @SerialName("encryption_tool_id")
    val encryption_tool_id:String,
    @SerialName("user_id")
    val user_id:String,
    @SerialName("updatedAt")
    val updatedAt:String,
    @SerialName("file")
    val file:FilesDto,
)
@Serializable
data class FilesDto(
    @SerialName("__type")
    val __type:String,
    @SerialName("name")
    val name:String,
    @SerialName("url")
    val url:String,
)