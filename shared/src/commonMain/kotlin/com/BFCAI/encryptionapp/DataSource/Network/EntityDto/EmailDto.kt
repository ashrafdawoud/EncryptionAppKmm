package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailDto (
    @SerialName("email")
    var email : String,
    )