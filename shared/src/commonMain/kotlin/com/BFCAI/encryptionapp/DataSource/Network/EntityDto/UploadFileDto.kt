package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import io.ktor.http.content.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class UploadFileDto(
    @SerialName("file_type")
    var file_type: String,
    @SerialName("encryption_tool_id")
    var encryption_tool_id: String,
    @SerialName("user_id")
    var user_id: String,
    @SerialName("file")
    var file: FileDto,
)
@Serializable
data class FileDto (
    @SerialName("__type")
    var __type : String,
    @SerialName("name")
    var name : String,
    @SerialName("url")
    var url : String,

)