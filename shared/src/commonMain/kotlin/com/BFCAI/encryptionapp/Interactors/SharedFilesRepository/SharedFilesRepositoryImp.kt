package com.BFCAI.encryptionapp.Interactors.SharedFilesRepository

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.SharedFilesCalls.SharedFilesInterface
import com.BFCAI.encryptionapp.Domain.Model.SharedFilesModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SharedFilesRepositoryImp constructor(
    private val sharedFilesInterface: SharedFilesInterface
) :SharedFilesReposioty{
    override suspend fun getAllFiles(senderId: String): Flow<DataState<SharedFilesModel>> = flow{
        emit(DataState.loading())
        try {
            val sharedfilesModel = sharedFilesInterface.getAllFiles(senderId)
            emit(DataState.data(message = null , data = sharedfilesModel))
        }catch (e:Exception){
            var message=e.message.toString()
            println(message)
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("getAllFiles.ERROR")
                        .title("Error")
                        .description(message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }

    override suspend fun deleteFiles(objectId: String): Flow<DataState<String>> = flow{
        emit(DataState.loading())
        try {
            val deletestring = sharedFilesInterface.deleteFiles(objectId)
            emit(DataState.data(message = null , data = deletestring))
        }catch (e:Exception){
            var message=e.message.toString()
            println(message)
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("deleteFiles.ERROR")
                        .title("Error")
                        .description(message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss { }
                )
            )
        }
    }
}