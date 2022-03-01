package com.BFCAI.encryptionapp.DataSource.Network.EntityDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto (
    @SerialName("code")
    var code : Int,
    @SerialName("error")
    var error : String,
    )