package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactsDto(
    @SerialName("results")
    val results : List<ContactsItemDto>
)
@Serializable
data class ContactsItemDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("createdAt")
    val createdAt:String,
    @SerialName("updatedAt")
    val updatedAt:String,
    @SerialName("contact1")
    val contact1:SingleContactDto,
    @SerialName("contact2")
    val contact2:SingleContactDto,
)
@Serializable
data class SingleContactDto(
    @SerialName("objectId")
    val objectId:String,
    @SerialName("username")
    val username:String,
    @SerialName("emailVerified")
    val emailVerified:Boolean,
)

