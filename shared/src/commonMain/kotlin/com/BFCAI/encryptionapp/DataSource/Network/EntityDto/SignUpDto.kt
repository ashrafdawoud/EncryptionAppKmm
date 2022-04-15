package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto(
    val password:String,
    val username:String,
    val email:String,
    val emailCopy:String,
)