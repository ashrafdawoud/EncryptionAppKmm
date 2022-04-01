package com.BFCAI.encryptionapp.Interactors.UserFileRepository

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserFilesCalls.UserFilesInterface
import com.BFCAI.encryptionapp.Domain.Model.UserFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import io.ktor.client.call.*
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserFileRepositoryImp constructor(
        val uploadInterface:UserFilesInterface
    ) :UserFileRepository {
    override suspend fun uploadFile(file: ByteArray,filename :String,filetype :String,encryptionTool :String,userid :String,token:String): Flow<DataState<String>> = flow{
        emit(DataState.loading())
        try {
            val data = uploadInterface.uploadFile(file ,filename,filetype,encryptionTool,userid,token)
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

    override suspend fun getAllFiles(userId: String,token:String): Flow<DataState<UserFilesModel>> = flow{
        emit(DataState.loading())
        try {
            val userFilesModel = uploadInterface.getAllFiles(userId,token)
            emit(DataState.data(data = userFilesModel , message = null))
        }catch (e:Exception){
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("getAllData.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }

    override suspend fun deleteFile(objectId: String,token:String): Flow<DataState<String>> = flow{
       emit(DataState.loading())
        try {
            val result = uploadInterface.deleteFile(objectId = objectId,token)
            if (result.equals("sucsess")){
                emit(DataState.data(data = result , message = null))
            }else{
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("deleteFile.ERROR")
                            .title("Error")
                            .description("Cant Delete File")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss { }
                    )
                )
            }
        }catch (e:Exception){
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("deleteFile.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }
}