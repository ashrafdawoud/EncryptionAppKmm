package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.Serializable

@Serializable
data class ShareFileDto(
    val fileDto: ObjectDto,
    val sender: ObjectDto,
    val reciver: ObjectDto,

)

@Serializable
data class ObjectDto(
    val __type : String,
    val className : String,
    val objectId : String,
)