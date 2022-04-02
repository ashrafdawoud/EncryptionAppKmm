package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.Serializable

@Serializable
data class AddContactDto(
    val contact1 : Contact,
    val contact2 : Contact,
)
@Serializable
data class Contact(
    val __type : String,
    val className : String,
    val objectId : String,
)
