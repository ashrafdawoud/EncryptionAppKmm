package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

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
    @SerialName("encryption_tool_id")
    val encryption_tool_id :String,
    @SerialName("file_type")
    val file_type :String,
    @SerialName("file")
    val file :FileDto,
    @SerialName("sender")
    val sender :SenderOrReciverDto,
    @SerialName("reciver")
    val reciver :SenderOrReciverDto,
    @SerialName("password")
    val password :String,
        )
@Serializable
data class SenderOrReciverDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("username")
    val username:String,
    @SerialName("emailVerified")
    val emailVerified:Boolean,

)