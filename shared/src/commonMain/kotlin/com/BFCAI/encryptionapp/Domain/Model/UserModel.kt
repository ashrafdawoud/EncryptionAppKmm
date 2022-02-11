package com.BFCAI.encryptionapp.Domain.Model

import kotlinx.serialization.SerialName

data class UserModel(
    val objectId: String,

    val username: String,

    val email: String,

    val emailVerified: Boolean,

    val sessionToken: String,
)
