package com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UploadFileDto
import io.ktor.http.content.*

interface UploadFileInterface {
    suspend fun uploadFile(file :ByteArray,filename :String) : String
}