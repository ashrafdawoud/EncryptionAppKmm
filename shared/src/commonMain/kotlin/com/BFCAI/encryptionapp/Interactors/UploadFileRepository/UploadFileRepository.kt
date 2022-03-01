package com.BFCAI.encryptionapp.Interactors.UploadFileRepository

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UploadFileDto
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import io.ktor.http.content.*
import kotlinx.coroutines.flow.Flow

interface UploadFileRepository {
    suspend fun uploadFile(file :ByteArray,filename :String) : Flow<DataState<String>>

}