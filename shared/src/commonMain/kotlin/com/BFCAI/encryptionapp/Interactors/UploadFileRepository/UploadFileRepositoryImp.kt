package com.BFCAI.encryptionapp.Interactors.UploadFileRepository

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ResponseDto
import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.UploadFileDto
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UploudFilesCalls.UploadFileInterface
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.http.content.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UploadFileRepositoryImp constructor(
        val uploadInterface:UploadFileInterface
    )
    :UploadFileRepository {
    override suspend fun uploadFile(file : ByteArray ,filename :String): Flow<DataState<String>> = flow{
        emit(DataState.loading())
        try {
            val data = uploadInterface.uploadFile(file ,filename)
            emit(DataState.data(message = null,data = data))
        }catch (e:Exception) {
            if (e is ClientRequestException) {
                var res = e.response.receive<String>()
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(res.toString() ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss { }
                    )
                )
            } else {
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(e.message ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss { }
                    )
                )
            }
        }
    }
}