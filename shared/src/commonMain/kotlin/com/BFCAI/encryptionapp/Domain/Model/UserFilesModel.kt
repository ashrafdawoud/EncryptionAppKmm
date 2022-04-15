package com.BFCAI.encryptionapp.Domain.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class UserFilesModel (
    val results :List<UserFilesItemModel>
)
data class UserFilesItemModel(
    val objectId:String,
    val file_type:String,
    val encryption_tool_id:String,
    val user_id:String,
    val updatedAt:String,
    val file: FilesModel,
)
data class FilesModel(
    val __type:String,
    val name:String,
    val url:String,
)