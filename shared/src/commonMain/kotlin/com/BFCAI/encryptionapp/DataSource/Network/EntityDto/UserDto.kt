package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("objectId")
    var objectId: String,

    @SerialName("username")
    var username: String,

    @SerialName("email")
    var email: String,

    @SerialName("emailVerified")
    var emailVerified: Boolean,

    @SerialName("sessionToken")
    var sessionToken: String,
)